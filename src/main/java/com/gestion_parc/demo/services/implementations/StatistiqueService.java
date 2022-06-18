package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.*;
import com.gestion_parc.demo.services.interfaces.IStatistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatistiqueService implements IStatistique {

    @Autowired
    VehiculeService vehiculeService;
    @Autowired
    AssuranceService assuranceService;
    @Autowired
    SinistreService sinistreService;
    @Autowired
    VignetteFiscaleService vignetteFiscaleService;
    @Autowired
    VidangeService vidangeService;
    @Autowired
    PneumatiqueService pneumatiqueService;
    @Autowired
    MaintenanceInterneService maintenanceInterneService;
    @Autowired
    MaintenanceExterneService maintenanceExterneService;

    @Override
    public List<Statistique> findByVehicule(Long id) {
        Vehicule vehicule=vehiculeService.findById(id);

        LocalDate now=LocalDate.now();
        LocalDate from=now.minusMonths(12);

        List<Assurance> assurances = assuranceService.findAllByVehiculeIdAndYear(id , from);
        List<Sinistre> sinistres = sinistreService.findAllByVehiculeIdAndYear(id , from);
        List<VignetteFiscale> vignetteFiscales = vignetteFiscaleService.findAllByVehiculeAndYear(id , from );
        List<Vidange> vidanges = vidangeService.findAllByVehiculeAndYear(id , from);
        List<Pneumatique> pneumatiques = pneumatiqueService.findAllByVehiculeIdAndYear(id , from );
        List<MaintenanceInterne> maintenanceInternes=maintenanceInterneService.findAllByVehiculeAndYear(id , from);
        List<MaintenanceExterne> maintenanceExternes=maintenanceExterneService.findAllByVehiculeAndYear(id , from);



        List<Statistique> statistiques=new ArrayList<>();
        while (from.isBefore(now.plusMonths(1))){
            String month=new DateFormatSymbols().getMonths()[from.getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(from.getYear());
            month=year+" - "+month;
            Double cost=0.0;
            Statistique statistique=new Statistique() ;
            statistique.setCost(cost);
            statistique.setMonth(month);
            statistiques.add(statistique);
            from=from.plusMonths(1);
        }

        for (Assurance assurance:assurances) {
            String month=new DateFormatSymbols().getMonths()[assurance.getDateDebut().getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(assurance.getDateDebut().getYear());
            month=year+" - "+month;
            Double cost=assurance.getCout();
            for (Statistique statistique:statistiques) {
                if(statistique.getMonth().equals(month)){
                    statistique.setCost(statistique.getCost()+cost);
                    break;
                }
            }
        }

        for (Sinistre sinistre:sinistres) {
            String month=new DateFormatSymbols().getMonths()[sinistre.getDate().getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(sinistre.getDate().getYear());
            month=year+" - "+month;
            Double cost=sinistre.getCoutMaintenance()*(100-sinistre.getPourcentageAssurer())/100;

            for (Statistique statistique:statistiques) {
                if(statistique.getMonth().equals(month)){
                    statistique.setCost(statistique.getCost()+cost);
                    break;
                }
            }

        }

        for (VignetteFiscale vignetteFiscale:vignetteFiscales) {
            String month=new DateFormatSymbols().getMonths()[vignetteFiscale.getDate().getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(vignetteFiscale.getDate().getYear());
            month=year+" - "+month;
            Double cost=vignetteFiscale.getMontantPrincipal();

            for (Statistique statistique:statistiques) {
                if(statistique.getMonth().equals(month)){
                    statistique.setCost(statistique.getCost()+cost);
                    break;
                }
            }
        }

        for (Vidange vidange:vidanges) {
            String month=new DateFormatSymbols().getMonths()[vidange.getDate().getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(vidange.getDate().getYear());
            month=year+" - "+month;
            Double cost=vidange.getCout();

            for (Statistique statistique:statistiques) {
                if(statistique.getMonth().equals(month)){
                    statistique.setCost(statistique.getCost()+cost);
                    break;
                }
            }

        }

        for (Pneumatique pneumatique:pneumatiques) {
            String month=new DateFormatSymbols().getMonths()[pneumatique.getDate().getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(pneumatique.getDate().getYear());
            month=year+" - "+month;
            Double cost=pneumatique.getCout();

            for (Statistique statistique:statistiques) {
                if(statistique.getMonth().equals(month)){
                    statistique.setCost(statistique.getCost()+cost);
                    break;
                }
            }
        }

        for (MaintenanceInterne maintenanceInterne:maintenanceInternes) {
            String month=new DateFormatSymbols().getMonths()[maintenanceInterne.getDateDebut().getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(maintenanceInterne.getDateDebut().getYear());
            month=year+" - "+month;
            Double cost=maintenanceInterne.getCout();

            for (Statistique statistique:statistiques) {
                if(statistique.getMonth().equals(month)){
                    statistique.setCost(statistique.getCost()+cost);
                    break;
                }
            }

        }

        for (MaintenanceExterne maintenanceExterne:maintenanceExternes) {
            String month=new DateFormatSymbols().getMonths()[maintenanceExterne.getDateDebut().getMonth().getValue()-1];
            month=month.substring(0,3);
            String year= String.valueOf(maintenanceExterne.getDateDebut().getYear());
            month=year+" - "+month;
            Double cost=maintenanceExterne.getCout();

            for (Statistique statistique:statistiques) {
                if(statistique.getMonth().equals(month)){
                    statistique.setCost(statistique.getCost()+cost);
                    break;
                }
            }

        }




        return statistiques;
    }

    @Override
    public Double findConsommationByVehicule(Long id) {
        Double cost=0.0;
        List<Assurance> assurances=assuranceService.findAllByVehiculeId(id);
        List<Sinistre> sinistres=sinistreService.findAllByVehiculeId(id);
        List<VignetteFiscale> vignetteFiscales=vignetteFiscaleService.findAllByVehicule(id);
        List<Vidange> vidanges=vidangeService.findAllByVehicule(id);
        List<Pneumatique> pneumatiques=pneumatiqueService.findAllByVehiculeId(id);
        List<MaintenanceInterne> maintenanceInternes=maintenanceInterneService.findAllByVehicule(id);
        List<MaintenanceExterne> maintenanceExternes=maintenanceExterneService.findAllByVehicule(id);

        for (Assurance assurance:assurances) {
            cost+=assurance.getCout();
        }
        for (Sinistre sinistre:sinistres) {
            cost+=sinistre.getCoutMaintenance()*(100-sinistre.getPourcentageAssurer());
        }
        for (VignetteFiscale vignetteFiscale:vignetteFiscales) {
            cost+=vignetteFiscale.getMontantPrincipal();
        }
        for (Vidange vidange:vidanges) {
            cost+=vidange.getCout();
        }
        for (Pneumatique pneumatique:pneumatiques) {
            cost+=pneumatique.getCout();
        }
        for (MaintenanceInterne maintenanceInterne:maintenanceInternes) {
            cost+=maintenanceInterne.getCout();
        }
        for (MaintenanceExterne maintenanceExterne:maintenanceExternes) {
            cost+=maintenanceExterne.getCout();
        }
        return cost;
    }
}
