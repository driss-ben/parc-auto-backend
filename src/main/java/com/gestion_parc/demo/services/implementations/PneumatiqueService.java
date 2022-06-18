package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Pneu;
import com.gestion_parc.demo.beans.Pneumatique;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.repositories.PneuRepos;
import com.gestion_parc.demo.repositories.PneumatiqueRepos;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.services.interfaces.IPneumatique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PneumatiqueService implements IPneumatique {

    @Autowired
    PneumatiqueRepos pneumatiqueRepos;

    @Autowired
    PneuRepos pneuRepos;

    @Autowired
    VehiculeRepos vehiculeRepos;

    @Override
    public Pneumatique findById(Long id) {
        return pneumatiqueRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Pneumatique add(Pneumatique pneumatique) {
        if (pneumatique.getPneu().getId()==null || pneumatique.getVehicule().getId()==null){
            throw new IllegalStateException("La vehicule ou pneu n'est pas selectionnee. ");
        }else {
            Pneu pneu = pneuRepos.findById(pneumatique.getPneu().getId())
                    .stream().findFirst().orElse(null);


            Vehicule vehicule = vehiculeRepos.findById(pneumatique.getVehicule().getId())
                    .stream().findFirst().orElse(null);

            pneumatique.setVehicule(vehicule);

            if (pneu.getQuantiteStock() - pneumatique.getNombrePneus() >= 0) {
                pneumatique.setCout( pneumatique.getNombrePneus() * pneu.getCout() );
                pneumatique.setKilometrage( pneumatique.getVehicule().getKilometrage() );
                pneu.setQuantiteStock(pneu.getQuantiteStock() - pneumatique.getNombrePneus());
                pneuRepos.save(pneu);
                pneumatiqueRepos.save(pneumatique);
                return pneumatique;
            } else {
                // Quantity is not enough exception
                throw new IllegalStateException("quantite des pneus insuffisante.");
            }
        }

    }

    @Override
    public Boolean update(Pneumatique pneumatique) {

        Pneumatique pneum = pneumatiqueRepos.findById(pneumatique.getId()).stream().findFirst().orElse(null);
        if (pneum != null) {
            Pneu newPneu = pneumatique.getPneu();
            Vehicule vehicule = pneumatique.getVehicule();
            Pneu oldPneu = pneum.getPneu();

            //Changing the pneu
            if (!oldPneu.equals(newPneu)) {
                //Enough quantity
                if (newPneu.getQuantiteStock() >= pneumatique.getNombrePneus()) {
                    oldPneu.setQuantiteStock(oldPneu.getQuantiteStock() + pneum.getNombrePneus());
                    pneuRepos.save(oldPneu);
                    newPneu.setQuantiteStock(newPneu.getQuantiteStock() - pneumatique.getNombrePneus());
                    pneumatique.setCout(newPneu.getCout() * pneumatique.getNombrePneus());
                    pneumatiqueRepos.save(pneumatique);
                    pneuRepos.save(newPneu);
                    return true;
                }
                //Quantity is not enough
                else {
                    throw new IllegalStateException("Quantite insuffisante des pneus");
                }
            }
            // The same pneu
            if (pneum.getPneu() == pneumatique.getPneu()) {
                // Enough quantity
                if (oldPneu.getQuantiteStock() + pneum.getNombrePneus() >= pneumatique.getNombrePneus()) {
                    oldPneu.setQuantiteStock(oldPneu.getQuantiteStock() + pneum.getNombrePneus() - pneumatique.getNombrePneus());
                    pneumatique.setCout(pneumatique.getPneu().getCout()*pneumatique.getNombrePneus());
                    pneuRepos.save(oldPneu);
                    pneumatiqueRepos.save(pneumatique);
                    return true;
                } else {
                    throw new IllegalStateException("Quantite insuffisante des pneus");
                }
            }
        }
        throw new IllegalStateException("L'operation de pneumatique n'existe pas !");
    }

    @Override
    public Boolean deleteById(Long id) {
        try{
            Pneumatique pneumatique=this.findById(id);
            Pneu pneu=pneumatique.getPneu();
            pneu.setQuantiteStock(pneu.getQuantiteStock()+pneumatique.getNombrePneus());
            pneumatiqueRepos.deleteById(id);
            pneuRepos.save(pneu);
            return true;
        }
        catch (Exception e){
            throw new IllegalStateException("Impossible de supprimer l'operation de pneumatique");
        }

    }

    @Override
    public void delete(Pneumatique pneumatique) {
        pneumatiqueRepos.delete(pneumatique);
    }

    @Override
    public List<Pneumatique> findAllByPneuId(Long id) {
        return pneumatiqueRepos.findAllByPneuId(id);
    }

    @Override
    public List<Pneumatique> findAllByVehiculeId(Long id) {
        return pneumatiqueRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<Pneumatique> findAllByVehiculeIdAndYear(Long id , LocalDate localDate) {
        return pneumatiqueRepos.findAllByVehiculeIdAndDateAfter(id,localDate);
    }

    @Override
    public List<Pneumatique> findAll() {
        return pneumatiqueRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public Pneumatique findLastByVehicule(Vehicule vehicule) {
        return pneumatiqueRepos.findLastByVehicule(vehicule);
    }

}
