package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Assurance;
import com.gestion_parc.demo.beans.Vehicule;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IAssurance {

    public Assurance add(Assurance assurance);
    public Assurance findById(Long id);
    public List<Assurance> findAll();
    public List<Assurance> findAllByVehiculeId(Long id);
    public List<Assurance> findAllByVehiculeIdAndYear(Long id , LocalDate localDate);
    public void delete(Assurance assurance);
    public void deleteById(Long id);
    public Assurance update(Assurance assurance);
    public Assurance findLastByVehicule(Vehicule vehicule);
    public Assurance findLastByVehicule(Long id);

}
