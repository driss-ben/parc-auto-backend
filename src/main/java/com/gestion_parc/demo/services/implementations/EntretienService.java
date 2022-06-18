package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.DemandeIntervention;
import com.gestion_parc.demo.beans.Entretien;
import com.gestion_parc.demo.repositories.DemandeInterventionRepos;
import com.gestion_parc.demo.repositories.EntretienRepos;
import com.gestion_parc.demo.services.interfaces.IEntretien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EntretienService implements IEntretien {


    @Autowired
    EntretienRepos entretienRepos;

    @Autowired
    DemandeInterventionRepos demandeInterventionRepos;

    @Override
    public List<Entretien> findAllByTypeIntervention(String type) {
        return entretienRepos.findAllByTypeIntervention(type);
    }

    @Override
    public Entretien add(Entretien entretien) {
        if (entretien.getDemandeIntervention()==null){
            throw new IllegalStateException("Selectionner la Demande d'intervention.");
        }else {
            DemandeIntervention demandeIntervention = entretien.getDemandeIntervention();
            if (demandeIntervention.getId() != null) {
                demandeIntervention = demandeInterventionRepos.findById(demandeIntervention.getId()).
                        stream().findFirst().orElse(null);
            } else {
                demandeInterventionRepos.save(demandeIntervention);
            }
            demandeIntervention.setEtat(true);
            demandeInterventionRepos.save(demandeIntervention);
            entretien.setDemandeIntervention(demandeIntervention);
            entretienRepos.save(entretien);
            return entretien;
        }
    }

    @Override
    public Entretien update(Entretien entretien) {
        Entretien ent=
                entretienRepos.findById(entretien.getId()).stream().findFirst().orElse(null);
        if(ent!=null){
            return this.add(entretien);
        }
        else {
            // Doesnt exist exception
            return null;
        }

    }

    @Override
    public Entretien findById(Long id) {
        return entretienRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public List<Entretien> findAll() {
        return entretienRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Entretien> findAllByTypeInterventionAndEtat(String type) {
        return entretienRepos.findAllByTypeInterventionAndEtat(type,false);
    }

    @Override
    public Entretien findByDemandeInterventionId(Long id) {
        return entretienRepos.findByDemandeInterventionId(id);
    }

    @Override
    public List<Entretien> findAllByVehiculeId(Long id) {
        List<DemandeIntervention> demandeInterventions=demandeInterventionRepos.findAllByVehiculeId(id);
        List<Entretien> entretiens=new ArrayList<>();
        for (DemandeIntervention demandeIntervention:demandeInterventions) {
            Entretien entretien=entretienRepos.findByDemandeIntervention(demandeIntervention);
            if(entretien != null){
                entretiens.add(entretien);
            }
        }
        return entretiens;
    }

    @Override
    public void delete(Entretien entretien) {
        try {
            entretienRepos.delete(entretien);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        Entretien entretien=entretienRepos.findById(id).stream().findFirst().orElse(null);
        if(entretien != null){
            if( ! entretien.getEtat() ){
                try {
                    DemandeIntervention demandeIntervention=entretien.getDemandeIntervention();
                    demandeIntervention.setEtat(false);
                    entretienRepos.deleteById(id);
                    demandeInterventionRepos.save(demandeIntervention);
                    return true;
                }
                catch (Exception e){
                    throw new IllegalStateException("Erreur lors de la suppression de l'entretien");
                }
            }
           throw new IllegalStateException("Impossible de supprimer l'entretien : l'entretien a ete deja traiter");
        }
        throw new IllegalStateException("L'entretien n'exist pas !");
    }

    @Override
    public List<Entretien> findAllByDate(LocalDate date) {
        return entretienRepos.findAllByDate(date);
    }

}
