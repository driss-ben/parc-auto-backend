package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.*;
import com.gestion_parc.demo.repositories.NotificationRepos;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.services.interfaces.IVehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculeService implements IVehicule {

    @Autowired
    VehiculeRepos vehiculeRepos;

    @Autowired
    AssuranceService assuranceService;

    @Autowired
    ContratVehiculeService contratVehiculeService;

    @Autowired
    CarteGriseService carteGriseService;

    @Autowired
    PneumatiqueService pneumatiqueService;

    @Autowired
    VidangeService vidangeService;

    @Autowired
    NotificationRepos notificationRepos;


    @Override
    public Vehicule add(Vehicule vehicule) {
        Vehicule veh=this.findByImmatriculation(vehicule.getImmatriculation());
        if(veh == null){
            vehiculeRepos.save(vehicule);
        }else{
            throw new IllegalStateException("la vehicule deja existe");
        }
        return  vehicule;
    }

    @Override
    public Vehicule update(Vehicule vehicule) {

            Vehicule veh=
                    vehiculeRepos.findById(vehicule.getId()).stream().findFirst().orElse(null);
            if(veh != null){
                return this.add(vehicule);
            }else{
                // Element doesnt exist exception
                throw new IllegalStateException("aucune vehicule avec id = " + vehicule.getId());
            }

    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        try {
            vehiculeRepos.deleteById(id);
        }
        catch (Exception e){

        }

    }

    @Override
    public Vehicule findByImmatriculation(String immatriculation) {
        return vehiculeRepos.findByImmatriculation(immatriculation);
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Vehicule> findAllWithoutCarteGrise() {
        List<Vehicule> vehiculeList=new ArrayList<>();
        List<Vehicule> vehicules=vehiculeRepos.findAll();
        List<CarteGrise> carteGrises=carteGriseService.findAll();
        for (Vehicule vehicule:vehicules) {
            int cp = 0;
            for (CarteGrise carteGrise:carteGrises) {
                if(carteGrise.getVehicule().equals(vehicule)){
                    cp++;
                }
            }
            if (cp==0) vehiculeList.add(vehicule);
        }
        return vehiculeList;
    }

    @Override
    public List<Vehicule> findAllNeedsAssurance() {

        List<ContratVehicule> contratVehicules=contratVehiculeService.findAllByType("achat");
        List<Vehicule> vehicules=new ArrayList<>();
        for (ContratVehicule contratVehicule:contratVehicules) {
            Vehicule vehicule=vehiculeRepos.findById(contratVehicule.getVehicule().getId())
                    .stream().findFirst().orElse(null);
            Assurance assurance=assuranceService.findLastByVehicule(vehicule);
            if(assurance != null){
                LocalDate dateAfter30 =  assurance.getDateFin().minusDays(30);
                LocalDate dateAfter20 =  assurance.getDateFin().minusDays(20);
                LocalDate dateAfter10 =  assurance.getDateFin().minusDays(10);
                if( LocalDate.now().isAfter(dateAfter10)){
                    Notification notification = notificationRepos.
                            findByVehiculeAndDateAndTitle(vehicule,dateAfter10,"Assurance");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Assurance");
                        notification.setDescription("l'assurance de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                "va terminer dans 10 jours");
                        notification.setDate(dateAfter10);
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }else if( LocalDate.now().isAfter(dateAfter20)){
                    Notification notification = notificationRepos.
                            findByVehiculeAndDateAndTitle(vehicule,dateAfter20,"Assurance");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Assurance");
                        notification.setDescription("l'assurance de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                "va terminer dans 20 jours");
                        notification.setDate(dateAfter20);
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }else if( LocalDate.now().isAfter(dateAfter30)){
                    Notification notification = notificationRepos.
                            findByVehiculeAndDateAndTitle(vehicule,dateAfter30,"Assurance");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Assurance");
                        notification.setDescription("l'assurance de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                "va terminer dans 30 jours");
                        notification.setDate(dateAfter30);
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }
            }
            else{
                vehicules.add(vehicule);
            }
       }
        return vehicules;
    }

    @Override
    public List<Vehicule> findAllNeedsVidange() {
        List<Vehicule> vehicules=new ArrayList<>();
        List<Vehicule> vehiculeList=vehiculeRepos.findAll();
        for (Vehicule vehicule:vehiculeList) {
            Vidange lastVidange=vidangeService.findLastByVehicule(vehicule);
            if(lastVidange!=null){
                if(vehicule.getKilometrage() == lastVidange.getKilometrage()
                        + // les alertes s'affichent avant 100km du prochain operation vidange
                        lastVidange.getHuile().getKilometrage() - 100){
                    Notification notification = notificationRepos.
                            findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Vidange");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Vidange");
                        notification.setDescription("le prochain vidange de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                "il le reste 100Km");
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }
                if(vehicule.getKilometrage() == lastVidange.getKilometrage()
                        + // les alertes s'affichent avant 100km du prochain operation vidange
                        lastVidange.getHuile().getKilometrage() - 50){
                    Notification notification = notificationRepos.
                            findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Vidange");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Vidange");
                        notification.setDescription("le prochain vidange de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                "il le reste 50Km");
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }
                if(vehicule.getKilometrage() == lastVidange.getKilometrage()
                        + // les alertes s'affichent avant 100km du prochain operation vidange
                        lastVidange.getHuile().getKilometrage() - 10){
                    Notification notification = notificationRepos.
                            findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Vidange");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Vidange");
                        notification.setDescription("le prochain vidange de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                "il le reste 10Km");
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }
            }
            else{
                Notification notification = notificationRepos.
                        findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Vidange");
                if (notification==null) {
                    notification =new Notification();
                    notification.setTitle("Vidange");
                    notification.setDescription(" la vehicule d'immatriculation "
                            + vehicule.getImmatriculation() +
                            " n'a pas de vidange ");
                    notification.setVehicule(vehicule);
                    notification.setKilometrageActuel(vehicule.getKilometrage());
                    notificationRepos.save(notification);
                }
                vehicules.add(vehicule);
            }
        }

        return vehicules;
    }

    @Override
    public List<Vehicule> findAllNeedsPneumatique() {
        List<Vehicule> vehicules=new ArrayList<>();
        List<Vehicule> vehiculeList=vehiculeRepos.findAll();
        for (Vehicule vehicule: vehiculeList) {
            Pneumatique pneumatique=pneumatiqueService.findLastByVehicule(vehicule);
            if(pneumatique==null){
                Notification notification = notificationRepos.
                        findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Pneumatique");
                if (notification==null) {
                    notification =new Notification();
                    notification.setTitle("Pneumatique");
                    notification.setDescription("la vehicule d'immatriculation "
                            + vehicule.getImmatriculation() +
                            " n'a aucune operation de pneumatique pour le moment");
                    notification.setVehicule(vehicule);
                    notification.setKilometrageActuel(vehicule.getKilometrage());
                    notificationRepos.save(notification);
                }
                vehicules.add(vehicule);
            }
            else{
               if(vehicule.getKilometrage() == pneumatique.getKilometrage()
                       + // fait - 100
                       pneumatique.getPneu().getKilometrage() - 100){
                   Notification notification = notificationRepos.
                           findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Pneumatique");
                   if (notification==null){
                       notification =new Notification();
                       notification.setTitle("Pneumatique");
                       notification.setDescription("le prochain pneumatique de la vehicule d'immatriculation "
                               + vehicule.getImmatriculation() +
                               " il le reste 100Km");
                       notification.setVehicule(vehicule);
                       notification.setKilometrageActuel(vehicule.getKilometrage());
                       notificationRepos.save(notification);
                   }

                   vehicules.add(vehicule);
               }
                if(vehicule.getKilometrage() == pneumatique.getKilometrage()
                        + // fait - 50
                        pneumatique.getPneu().getKilometrage() - 50){
                    Notification notification = notificationRepos.
                            findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Pneumatique");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Pneumatique");
                        notification.setDescription("le prochain pneumatique de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                " il le reste 50Km");
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }
                if(vehicule.getKilometrage() == pneumatique.getKilometrage()
                        + // fait - 10
                        pneumatique.getPneu().getKilometrage() - 10){
                    Notification notification = notificationRepos
                            .findByVehiculeAndKilometrageActuelAndTitle(vehicule,vehicule.getKilometrage(),"Pneumatique");
                    if (notification==null) {
                        notification =new Notification();
                        notification.setTitle("Pneumatique");
                        notification.setDescription("le prochain pneumatique de la vehicule d'immatriculation "
                                + vehicule.getImmatriculation() +
                                "il le reste 10Km");
                        notification.setVehicule(vehicule);
                        notification.setKilometrageActuel(vehicule.getKilometrage());
                        notificationRepos.save(notification);
                    }
                    vehicules.add(vehicule);
                }
            }
        }
        return vehicules;
    }

}
