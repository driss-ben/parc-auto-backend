package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.VignetteFiscale;

import java.time.LocalDate;
import java.util.List;

public interface IVignetteFiscale {

    VignetteFiscale add(VignetteFiscale vignetteFiscale);
    VignetteFiscale findById(Long id);
    VignetteFiscale update(VignetteFiscale vignetteFiscale);
    void delete(VignetteFiscale vignetteFiscale);
    void deleteById(Long id);
    List<VignetteFiscale> findAll();
    List<VignetteFiscale> findAllByVehicule(Long id);
    List<VignetteFiscale> findAllByVehiculeAndYear(Long id , LocalDate localDate);

}
