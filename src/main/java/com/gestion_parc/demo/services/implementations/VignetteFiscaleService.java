package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.beans.VignetteFiscale;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.repositories.VignetteFiscaleRepos;
import com.gestion_parc.demo.services.interfaces.IVignetteFiscale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VignetteFiscaleService implements IVignetteFiscale {

    @Autowired
    VignetteFiscaleRepos vignetteFiscaleRepos;

    @Autowired
    VehiculeRepos vehiculeRepos;

    @Override
    public VignetteFiscale add(VignetteFiscale vignetteFiscale) {
            if (vignetteFiscale.getVehicule().getId()==null){
                throw new IllegalStateException("La vehicule n'est pas selectionnee.");
            }else {
                Vehicule vehicule = vignetteFiscale.getVehicule();
                if (vehicule.getId() != null) {
                    vehicule = vehiculeRepos.findById(vehicule.getId()).stream().findFirst().orElse(null);
                } else {
                    vehiculeRepos.save(vehicule);
                }
                vignetteFiscale.setVehicule(vehicule);

                VignetteFiscale lastVignette = vignetteFiscaleRepos.findLastByVehicule(vehicule);
                if (lastVignette != null) {
                    LocalDate date = lastVignette.getDate();
                    LocalDate thisYear = LocalDate.now();
                    date.plusYears(1);
                    if (date.isBefore(thisYear)) {
                        vignetteFiscaleRepos.save(vignetteFiscale);
                        return vignetteFiscale;
                    } else {
                        // The last Vignette is not expired exception
                        return null;
                    }
                } else {
                    vignetteFiscaleRepos.save(vignetteFiscale);
                    return vignetteFiscale;
                }
            }


    }

    @Override
    public VignetteFiscale findById(Long id) {
        return vignetteFiscaleRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public VignetteFiscale update(VignetteFiscale vignetteFiscale) {
        VignetteFiscale vignette=vignetteFiscaleRepos.
                findById(vignetteFiscale.getId()).stream().findFirst().orElse(null);

        if(vignette != null){
            Vehicule vehicule=vignetteFiscale.getVehicule();
            if(vehicule.getId() != null){
                vehicule=vehiculeRepos.findById(vehicule.getId()).stream().findFirst().orElse(null);
            }
            vignetteFiscale.setVehicule(vehicule);
            vignetteFiscaleRepos.save(vignetteFiscale);
            return vignetteFiscale;
        }
        else {
            // Doesnt exist exception
            return null;
        }

    }

    @Override
    public void delete(VignetteFiscale vignetteFiscale) {
        try {
            vignetteFiscaleRepos.delete(vignetteFiscale);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            vignetteFiscaleRepos.deleteById(id);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public List<VignetteFiscale> findAll() {
        return vignetteFiscaleRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<VignetteFiscale> findAllByVehicule(Long id) {
        return vignetteFiscaleRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<VignetteFiscale> findAllByVehiculeAndYear(Long id , LocalDate localDate) {
        return vignetteFiscaleRepos.findAllByVehiculeIdAndDateAfter(id,localDate);
    }
}
