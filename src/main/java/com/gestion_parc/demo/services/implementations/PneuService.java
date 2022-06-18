package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Pneu;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.repositories.PneuRepos;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.services.interfaces.IPneu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PneuService implements IPneu {

    @Autowired
    private PneuRepos pneuRepos;

    @Autowired
    private VehiculeRepos vehiculeRepos;

    @Override
    public Pneu findByNom(String nom) {
        return pneuRepos.findByNom(nom);
    }

    @Override
    public void deleteByNom(String nom) {
        pneuRepos.deleteByNom(nom);
    }

    @Override
    public Pneu update(Pneu pneu) {
        Pneu pn=pneuRepos.findByNom(pneu.getNom());
        Pneu pneu1 = pneuRepos.findById(pneu.getId()).stream().findFirst().orElse(null);
        if(pn == null){
            pneuRepos.save(pneu);
        }else if (pn.getNom() == pneu1.getNom()){
            pneuRepos.save(pneu);
        }else {
            throw new IllegalStateException("Pneu existe deja avec ce nom");
        }
        return pneu;
    }

    @Override
    public Pneu findById(Long id) {
        return pneuRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Pneu pneu=pneuRepos.findById(id).stream().findFirst().orElse(null);
        if(pneu != null){
            pneu.setCout(0.0);
            pneu.setQuantiteStock(0L);
            pneuRepos.save(pneu);
        }
    }

    @Override
    public Pneu add(Pneu pneu) {
        Pneu pn=pneuRepos.findByNom(pneu.getNom());
        if(pn == null){
//            pneu.setQuantiteStock(pn.getQuantiteStock()+pneu.getQuantiteStock());
//            pneu.setCout((pn.getCout()+pneu.getCout())/pneu.getQuantiteStock());
            // Already exist operations
            pneuRepos.save(pneu);
        }else {
            throw new IllegalStateException("Pneu existe deja avec ce nom");
        }

        return pneu;
    }

    @Override
    public List<Pneu> findAll() {
        return pneuRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Pneu> findAllPneuStock() {
        return pneuRepos.findAllByQuantiteStockGreaterThan(0L);
    }

    @Override
    public List<Pneu> findAllByRayon(Long id) {
        Vehicule vehicule=vehiculeRepos.findById(id).stream().findFirst().orElse(null);
        if(vehicule != null){
            return pneuRepos.findAllByQuantiteStockGreaterThanAndRayonEquals(0L,vehicule.getRayonRoue());
        }
        else {
            return null;
        }

    }
}
