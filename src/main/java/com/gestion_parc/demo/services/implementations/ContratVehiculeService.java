package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.ContratVehicule;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.repositories.ContratVehiculeRepos;
import com.gestion_parc.demo.services.interfaces.IContratVehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContratVehiculeService implements IContratVehicule {

    @Autowired
    ContratVehiculeRepos contratVehiculeRepos;


    @Override
    public List<ContratVehicule> findAllByType(String type) {
        return contratVehiculeRepos.findAllByTypeEquals(type);
    }

    @Override
    public ContratVehicule add(ContratVehicule contratVehicule) {
        if (contratVehicule.getVehicule().getId() == null || contratVehicule.getType()==null )
            throw new IllegalStateException(" la vehicule ou type n'est pas selectionner.");
        else {
            contratVehiculeRepos.save(contratVehicule);
            return contratVehicule;
        }
    }

    @Override
    public ContratVehicule findById(Long id) {
        return contratVehiculeRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        contratVehiculeRepos.deleteById(id);
    }

    @Override
    public ContratVehicule update(ContratVehicule contratVehicule) {
        if (contratVehicule.getVehicule().getId() == null || contratVehicule.getType()==null)
            throw new IllegalStateException("Selectionner la vehicule.");
        else {
            contratVehiculeRepos.save(contratVehicule);
            return contratVehicule;
        }
    }
    @Override
    public List<ContratVehicule> findAll() {
        return contratVehiculeRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public ContratVehicule findByVehiculeLastRecord(Long id){
       List<ContratVehicule> contratVehicules = contratVehiculeRepos.findAll();
        List<ContratVehicule> list = new ArrayList<>();
        for (ContratVehicule contrat : contratVehicules ) {
            if (contrat.getVehicule().getId() == id) list.add(contrat);
        }
        if (list.size()==0) return null;
        else return  list.get(list.size()-1);
    }
}
