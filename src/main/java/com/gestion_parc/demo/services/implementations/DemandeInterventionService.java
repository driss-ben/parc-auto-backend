package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.DemandeIntervention;
import com.gestion_parc.demo.beans.Notification;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.repositories.DemandeInterventionRepos;
import com.gestion_parc.demo.repositories.NotificationRepos;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.services.interfaces.IDemandeIntervention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class DemandeInterventionService implements IDemandeIntervention {

    @Autowired
    DemandeInterventionRepos demandeInterventionRepos;
    @Autowired
    VehiculeRepos vehiculeRepos;
    @Autowired
    NotificationRepos notificationRepos;

    @Override
    public DemandeIntervention add(DemandeIntervention demandeIntervention) {
        if (demandeIntervention.getVehicule().getId() == null  )
            throw new IllegalStateException("Selectionner la vehicule.");
        else {
            Vehicule vehicule = vehiculeRepos.findById(demandeIntervention.getVehicule().getId())
                    .stream().findFirst().orElse(null);
            Notification notification = new Notification();
            notification.setTitle("Demande Intervention");
            notification.setVehicule(vehicule);
            notification.setDescription("une nouvelle demande d'intervention est ajoutee." +
                    "elle concerne la vehicule " + vehicule.getImmatriculation());
            notificationRepos.save(notification);
            demandeInterventionRepos.save(demandeIntervention);
        }
        return demandeIntervention;
    }

    @Override
    public DemandeIntervention update(DemandeIntervention demandeIntervention) {
        if (demandeIntervention.getVehicule().getId() == null  )
            throw new IllegalStateException("Selectionner la vehicule.");
        else {
            return this.add(demandeIntervention);
        }
    }

    @Override
    public DemandeIntervention setAsDone(DemandeIntervention demandeIntervention){
        DemandeIntervention demande = demandeInterventionRepos.getById(demandeIntervention.getId());
        if (demande.getEtat() == false)
            demande.setEtat(true);
        else  demande.setEtat(false);
        return demandeInterventionRepos.save(demande);
    }

    @Override
    public void delete(DemandeIntervention demandeIntervention) {
        demandeInterventionRepos.delete(demandeIntervention);
    }

    @Override
    public Boolean deleteById(Long id) {
        DemandeIntervention demandeIntervention=demandeInterventionRepos.findById(id).stream().findFirst().orElse(null);
        if( demandeIntervention != null){
            if ( ! demandeIntervention.getEtat() ){
                try {
                    demandeInterventionRepos.deleteById(id);
                    return true;
                }catch (Exception e){
                    throw new IllegalStateException("Erreur lors de la suppression !");
                }

            }
            throw new IllegalStateException("Impossible de supprimer la demande : la demande a ete deja traiter");
        }
        throw new IllegalStateException("Impossible de supprimer la demande : la demande n'existe pas");
    }

    @Override
    public List<DemandeIntervention> findAllByVehiculeId(Long id) {
        return demandeInterventionRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<DemandeIntervention> findAllByDate(LocalDate date) {
        return demandeInterventionRepos.findAllByDate(date);
    }

    @Override
    public List<DemandeIntervention> findAllByEtat(Boolean etat) {
        return demandeInterventionRepos.findAllByEtat(etat);
    }

    @Override
    public List<DemandeIntervention> findAll() {
        return demandeInterventionRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public DemandeIntervention findById(Long id) {
        return demandeInterventionRepos.findById(id).stream().findFirst().orElse(null);
    }
}
