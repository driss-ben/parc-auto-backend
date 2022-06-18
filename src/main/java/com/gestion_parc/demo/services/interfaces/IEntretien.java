package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.DemandeIntervention;
import com.gestion_parc.demo.beans.Entretien;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IEntretien {

    public List<Entretien> findAllByTypeIntervention(String type);
    public Entretien add(Entretien entretien);
    public Entretien update(Entretien entretien);
    public Entretien findById(Long id);
    public List<Entretien> findAll();
    public List<Entretien> findAllByTypeInterventionAndEtat(String type);
    public Entretien findByDemandeInterventionId(Long id);
    public List<Entretien> findAllByVehiculeId(Long id);
    public void delete(Entretien entretien);
    public Boolean deleteById(Long id);
    public List<Entretien> findAllByDate(LocalDate date);


}
