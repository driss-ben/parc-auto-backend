package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.beans.Vidange;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVidange {

    public Vidange add(Vidange vidange);
    public void delete(Vidange vidange);
    public Boolean deleteById(Long id);
    public Vidange findById(Long id);
    public Vidange update(Vidange vidange);
    public List<Vidange> findAll();
    public List<Vidange> findAllByHuile(Long id);
    public List<Vidange> findAllByVehicule(Long id);
    public List<Vidange> findAllByDate(LocalDate date);
    public Vidange findLastByVehicule(Vehicule vehicule);
    public List<Vidange> findAllByVehiculeAndYear(Long id , LocalDate localDate);

}
