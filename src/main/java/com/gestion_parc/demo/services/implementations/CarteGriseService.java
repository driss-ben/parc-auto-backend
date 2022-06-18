package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.CarteGrise;
import com.gestion_parc.demo.repositories.CarteGriseRepos;
import com.gestion_parc.demo.services.interfaces.ICarteGrise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarteGriseService implements ICarteGrise {

    @Autowired
    CarteGriseRepos carteGriseRepos;

    @Override
    public CarteGrise add(CarteGrise carteGrise) {
        if (carteGrise.getVehicule() == null )
            throw new IllegalStateException("Selectionner la vehicule.");
        else {
            CarteGrise carte = carteGriseRepos.findByVehiculeId(carteGrise.getVehicule().getId());
            if (carte == null) {
                carteGriseRepos.save(carteGrise);
                return carteGrise;
            } else throw new IllegalStateException("cette vehicule a deja une carte grise enregistre");
        }
    }

    @Override
    public CarteGrise update(CarteGrise carteGrise) {
        if (carteGrise.getVehicule().getId() == null )
            throw new IllegalStateException("Selectionner la vehicule.");
        else {
            CarteGrise carte = carteGriseRepos.
                    findById(carteGrise.getId()).stream().findFirst().orElse(null);
            if (carte != null) {
                carteGriseRepos.save(carteGrise);
                return carteGrise;
            } else {
                // Doesnt exist exception
                return null;
            }
        }

    }

    @Override
    public CarteGrise findById(Long id) {
        return carteGriseRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public void delete(CarteGrise carteGrise) {

        try {
            carteGriseRepos.delete(carteGrise);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }

    }

    @Override
    public void deleteById(Long id) {

        try {
            carteGriseRepos.deleteById(id);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public List<CarteGrise> findAll() {
        return carteGriseRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<CarteGrise> findAllByDateOperation(LocalDate date) {
        return carteGriseRepos.findAllByDateOperation(date);
    }

    @Override
    public List<CarteGrise> findAllByFinValidite(LocalDate date) {
        return carteGriseRepos.findAllByFinValidite(date);
    }

    @Override
    public CarteGrise findByProprietaire(String proprietaire) {
        return carteGriseRepos.findByProprietaire(proprietaire);
    }

    @Override
    public CarteGrise findByNumeroSerie(String numero) {
        return carteGriseRepos.findByNumeroSerie(numero);
    }

    @Override
    public CarteGrise findByVehiculeLastRecord(Long id){
        List<CarteGrise> carteGrises = carteGriseRepos.findAll();
        List<CarteGrise> list = new ArrayList<>();
        for (CarteGrise carte : carteGrises ) {
            if (carte.getVehicule().getId() == id) list.add(carte);
        }
        if (list.size()==0) return null;
        else return  list.get(list.size()-1);
    }
}
