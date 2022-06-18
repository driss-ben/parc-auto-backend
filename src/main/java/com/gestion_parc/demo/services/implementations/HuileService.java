package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Huile;
import com.gestion_parc.demo.beans.TypeHuile;
import com.gestion_parc.demo.repositories.HuileRepose;
import com.gestion_parc.demo.services.interfaces.IHuile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HuileService implements IHuile {

    @Autowired
    HuileRepose huileRepose;

    @Autowired
    TypeHuileService typeHuileService;

    @Override
    public Huile add(Huile huile) {
        if (huile.getTypeHuile()==null){
            throw new IllegalStateException("Le type d'huile n'est pas selectionnee. ");
        }else {
            Huile hui = huileRepose.findByNom(huile.getNom());

            TypeHuile typeHuile = huile.getTypeHuile();
            if (typeHuile.getId() != null) {
                typeHuile = typeHuileService.findById(typeHuile.getId());
            } else {
                typeHuileService.add(typeHuile);
            }
            huile.setTypeHuile(typeHuile);


            if (hui == null)
                huileRepose.save(huile);
            else throw new IllegalStateException("huile deja existe");
            return huile;
        }
    }

    @Override
    public Huile update(Huile huile) {
        if (huile.getTypeHuile().getId()==null){
            throw new IllegalStateException("Le type d'huile n'est pas selectionnee. ");
        }else {
            Huile hui = this.findById(huile.getId());
            if (hui != null) {
                if (huile.getTypeHuile() != null) {
                    TypeHuile typeHuile = huile.getTypeHuile();
                    if (typeHuile.getId() != null) {
                        typeHuile = typeHuileService.findById(typeHuile.getId());
                    } else {
                        typeHuileService.add(typeHuile);
                    }
                    huile.setTypeHuile(typeHuile);
                }
                huileRepose.save(huile);
                return huile;
            } else {
                // Doesnt exist exception
                return null;
            }
        }
    }

    @Override
    public void delete(Huile huile) {
        huileRepose.delete(huile);
    }

    @Override
    public void deleteById(Long id) {
        Huile huile=huileRepose.findById(id).stream().findFirst().orElse(null);
        if(huile != null){
            huile.setCout(0.0);
            huile.setQuantiteStock(0L);
            huileRepose.save(huile);
        }
    }

    @Override
    public Huile findById(Long id) {
        return huileRepose.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Huile findByNom(String nom) {
        return huileRepose.findByNom(nom);
    }

    @Override
    public List<Huile> findAll() {
        return huileRepose.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Huile> findAllHuileStock() {
        return huileRepose.findAllByQuantiteStockGreaterThan(0L);
    }
}
