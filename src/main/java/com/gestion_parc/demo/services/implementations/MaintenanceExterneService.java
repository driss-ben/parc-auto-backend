package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.*;
import com.gestion_parc.demo.repositories.*;
import com.gestion_parc.demo.services.interfaces.IMaintenanceExterne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class MaintenanceExterneService implements IMaintenanceExterne {

    @Autowired
    MaintenanceExterneRepos maintenanceExterneRepos;

    @Autowired
    PrestataireRepos prestataireRepos;

    @Autowired
    ServiceFaitRepos serviceFaitRepos;

    @Autowired
    EngagementRepos engagementRepos;

    @Autowired
    EntretienService entretienService;

    @Autowired
    EntretienRepos entretienRepos;

    @Override
    public MaintenanceExterne add(MaintenanceExterne maintenanceExterne) {
        if (maintenanceExterne.getEntretien().getId()==null){
            throw new IllegalStateException("Selectionner l'entretien tout d'abord");
        }else {
            Prestataire prestataire = maintenanceExterne.getPrestataire();
            ServiceFait serviceFait = maintenanceExterne.getServiceFait();
            Engagement engagement = maintenanceExterne.getEngagement();

            if (prestataire.getId() != null) {
                prestataire = prestataireRepos.findById(prestataire.getId()).
                        stream().findFirst().orElse(null);
            } else {
                prestataireRepos.save(prestataire);
            }
            maintenanceExterne.setPrestataire(prestataire);

            if (serviceFait.getId() != null) {
                serviceFait = serviceFaitRepos.findById(serviceFait.getId()).
                        stream().findFirst().orElse(null);
            } else {
                serviceFaitRepos.save(serviceFait);
            }
            maintenanceExterne.setServiceFait(serviceFait);

            if (engagement.getId() != null) {
                engagement = engagementRepos.findById(engagement.getId()).
                        stream().findFirst().orElse(null);
            } else {
                engagementRepos.save(engagement);
            }
            maintenanceExterne.setEngagement(engagement);

            Entretien entretien = maintenanceExterne.getEntretien();
            if (entretien.getId() != null) {
                entretien = entretienService.findById(entretien.getId());
            } else {
                entretien = entretienService.add(entretien);
            }
            entretien.setEtat(true);
            entretienRepos.save(entretien);
            maintenanceExterne.setEntretien(entretien);
            maintenanceExterne.setCout(serviceFait.getCout());
            maintenanceExterneRepos.save(maintenanceExterne);
            return maintenanceExterne;
        }
    }

    @Override
    public void delete(MaintenanceExterne maintenanceExterne) {
        maintenanceExterneRepos.delete(maintenanceExterne);
    }

    @Override
    public Boolean deleteById(Long id) {
        MaintenanceExterne maintenanceExterne=maintenanceExterneRepos.findById(id).stream().findFirst().orElse(null);
        if(maintenanceExterne != null){
            try {
                Entretien entretien=maintenanceExterne.getEntretien();
                entretien.setEtat(false);
                maintenanceExterneRepos.deleteById(id);
                entretienRepos.save(entretien);
                return true ;
            }
            catch (Exception e) {
                throw new IllegalStateException("Erreur lors de la suppression !");
            }
        }
        throw new IllegalStateException("La maintenance externe n'exist pas !");

    }

    @Override
    public List<MaintenanceExterne> findAll() {
        return maintenanceExterneRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<MaintenanceExterne> findAllByVehicule(Long id) {
        return maintenanceExterneRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<MaintenanceExterne> findAllByVehiculeAndYear(Long id , LocalDate localDate) {
        return maintenanceExterneRepos.findAllByVehiculeIdAndDateFinAfter(id,localDate);
    }

    @Override
    public MaintenanceExterne findById(Long id) {
        return maintenanceExterneRepos.findById(id)
                .stream().findFirst().orElse(null);
    }

    @Override
    public MaintenanceExterne update(MaintenanceExterne maintenanceExterne) {
        MaintenanceExterne maintenance=this.findById(maintenanceExterne.getId());
        if(maintenance != null){
            return this.add(maintenanceExterne);
        }
        else{
            // Doesnt exist exception
            return null ;
        }
    }
}
