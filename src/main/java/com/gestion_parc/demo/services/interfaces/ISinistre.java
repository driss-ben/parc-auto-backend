package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Sinistre;
import com.gestion_parc.demo.beans.Vehicule;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ISinistre {

    Sinistre add(Sinistre sinistre);
    Sinistre findById(Long id);
    Sinistre update(Sinistre sinistre);
    void delete(Sinistre sinistre);
    void deleteById(Long id);
    List<Sinistre> findAllByVehiculeId(Long id);
    List<Sinistre> findAllByVehiculeIdAndYear(Long id , LocalDate localDate);
    List<Sinistre> findAllByDate(LocalDate date);
    List<Sinistre> findAll();

}
