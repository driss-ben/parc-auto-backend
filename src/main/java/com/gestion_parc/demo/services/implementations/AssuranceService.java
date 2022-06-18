package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Assurance;
import com.gestion_parc.demo.beans.Assureur;
import com.gestion_parc.demo.beans.ContratVehicule;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.repositories.AssuranceRepos;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.services.interfaces.IAssurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AssuranceService implements IAssurance {

    @Autowired
    AssuranceRepos assuranceRepos;

    @Autowired
    AssureurService assureurService;

    @Autowired
    VehiculeRepos vehiculeRepos;



    @Override
    public Assurance add(Assurance assurance) {
        if (assurance.getAssureur().getId()==null || assurance.getVehicule().getId()==null)
            throw new IllegalStateException("Selectionner la vehicule et l'assureur");
        else {
            Vehicule vehicule = vehiculeRepos.
                    findById(assurance.getVehicule().getId()).stream().findFirst().orElse(null);
            assurance.setVehicule(vehicule);

            Assureur assureur = assurance.getAssureur();
            if (assureur.getId() != null) {
                assureur = assureurService.findById(assureur.getId());
            } else {
                assureur = assureurService.add(assureur);
            }

            assurance.setAssureur(assureur);

            assuranceRepos.save(assurance);
        }
        return assurance;
    }

    @Override
    public Assurance findById(Long id) {
        return assuranceRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public List<Assurance> findAll() {
        return assuranceRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Assurance> findAllByVehiculeId(Long id){
        return assuranceRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<Assurance> findAllByVehiculeIdAndYear(Long id , LocalDate localDate) {
        return assuranceRepos.findAllByVehiculeIdAndDateDebutAfter(id,localDate);
    }

    @Override
    public void delete(Assurance assurance) {
        assuranceRepos.delete(assurance);
    }

    @Override
    public void deleteById(Long id) {
        assuranceRepos.deleteById(id);
    }

    @Override
    public Assurance update(Assurance assurance) {
        return this.add(assurance);
    }


    @Override
    public Assurance findLastByVehicule(Vehicule vehicule) {
        return assuranceRepos.findLastByVehicule(vehicule);
    }

    @Override
    public Assurance findLastByVehicule(Long id) {
        Vehicule vehicule=vehiculeRepos.findById(id).stream().findFirst().orElse(null);
        if(vehicule != null){
            return this.findLastByVehicule(vehicule);
        }
        else {
            throw new IllegalStateException("Cette vehicule n'a aucune assurance.");
        }

    }

}
