package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.TypeHuile;
import com.gestion_parc.demo.repositories.TypeHuileRepos;
import com.gestion_parc.demo.services.interfaces.ITypeHuile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeHuileService implements ITypeHuile {

    @Autowired
    TypeHuileRepos typeHuileRepos;

    @Override
    public List<TypeHuile> findAll() {
        return typeHuileRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public TypeHuile findById(Long id) {
        return typeHuileRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public TypeHuile findByNom(String nom) {
        return typeHuileRepos.findByNom(nom);
    }

    @Override
    public Object add(TypeHuile typeHuile) {
        TypeHuile type=typeHuileRepos.findByNom(typeHuile.getNom());
        if(type==null){
            typeHuileRepos.save(typeHuile);
            return true;
        }
        else{
            //Already exist exception
            throw new IllegalStateException("type d'huile deja existe.");
        }
    }

    @Override
    public void delete(TypeHuile typeHuile) {
        typeHuileRepos.delete(typeHuile);
    }

    @Override
    public void deleteById(Long id) {
        typeHuileRepos.deleteById(id);
    }

    @Override
    public void deleteByNom(String nom) {
        typeHuileRepos.deleteByNom(nom);
    }

    @Override
    public TypeHuile update(TypeHuile typeHuile) {
        typeHuileRepos.save(typeHuile);
        return typeHuile;
    }
}
