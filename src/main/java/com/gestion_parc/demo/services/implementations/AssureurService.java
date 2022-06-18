package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Assureur;
import com.gestion_parc.demo.repositories.AssureurRepos;
import com.gestion_parc.demo.services.interfaces.IAssureur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssureurService implements IAssureur {

    @Autowired
    AssureurRepos assureurRepos;

    @Override
    public Assureur add(Assureur assureur) {
        if(assureur !=null){
            Assureur assur=this.findByNom(assureur.getNom());
            if(assur==null){
                assureurRepos.save(assureur);
            }
            else{
                // Already exist exception
                return assur;
            }
        }

        return assureur;
    }

    @Override
    public Assureur update(Assureur assureur) {
        if(assureurRepos.findById(assureur.getId()).stream().findFirst().orElse(null)!=null){
            assureurRepos.save(assureur);
            return assureur;
        }
        else {
            // Doesnt exist exception
            return null;
        }

    }

    @Override
    public Assureur findById(Long id) {
        return assureurRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Assureur findByNom(String nom) {
        return assureurRepos.findByNom(nom);
    }

    @Override
    public void deleteById(Long id) {
        assureurRepos.deleteById(id);
    }

    @Override
    public void deleteByNom(String nom) {
        assureurRepos.deleteByNom(nom);
    }

    @Override
    public void delete(Assureur assureur) {
        assureurRepos.delete(assureur);
    }

    @Override
    public List<Assureur> findAll() {
        return assureurRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

}
