package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.CarteGrise;
import com.gestion_parc.demo.beans.Vehicule;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ICarteGrise {

    CarteGrise add(CarteGrise carteGrise);
    CarteGrise update(CarteGrise carteGrise);
    CarteGrise findById(Long id);
    void delete(CarteGrise carteGrise);
    void deleteById(Long id);
    List<CarteGrise> findAll();
    List<CarteGrise>  findAllByDateOperation(LocalDate date);
    List<CarteGrise>  findAllByFinValidite(LocalDate date);
    CarteGrise findByProprietaire(String proprietaire);
    CarteGrise findByNumeroSerie(String numero);

    CarteGrise findByVehiculeLastRecord(Long id);
}
