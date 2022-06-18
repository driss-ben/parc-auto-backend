package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Sinistre;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.repositories.SinistreRepos;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.services.interfaces.ISinistre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class SinistreService implements ISinistre {

    @Autowired
    SinistreRepos sinistreRepos;

    @Autowired
    VehiculeRepos vehiculeRepos;

    @Override
    public Sinistre add(Sinistre sinistre) {
        if (sinistre.getVehicule().getId() == null) {
            throw new IllegalStateException("la vehicule n'est pas selectionner.");
        } else {
            Vehicule vehicule = sinistre.getVehicule();
            if (vehicule.getId() != null) {
                vehicule = vehiculeRepos.findById(vehicule.getId()).stream().findFirst().orElse(null);
            } else {
                vehiculeRepos.save(vehicule);
            }
            sinistre.setVehicule(vehicule);

            sinistreRepos.save(sinistre);
            return sinistre;
        }
    }

    @Override
    public Sinistre findById(Long id) {
        return sinistreRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Sinistre update(Sinistre sinistre) {
        if(this.findById(sinistre.getId()) !=null)
        {
            return this.add(sinistre);
        }
        else{
            // Doesnt exist exception
            return null;
        }

    }

    @Override
    public void delete(Sinistre sinistre) {

        try {
            sinistreRepos.delete(sinistre);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }

    }

    @Override
    public void deleteById(Long id) {

        try {
            sinistreRepos.deleteById(id);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }

    }

    @Override
    public List<Sinistre> findAllByVehiculeId(Long id) {
        return sinistreRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<Sinistre> findAllByVehiculeIdAndYear(Long id , LocalDate localDate) {
        return sinistreRepos.findAllByVehiculeIdAndDateAfter(id,localDate);
    }

    @Override
    public List<Sinistre> findAllByDate(LocalDate date) {
        return sinistreRepos.findAllByDate(date);
    }

    @Override
    public List<Sinistre> findAll() {
        return sinistreRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
}
