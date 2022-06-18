package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.DemandeIntervention;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IDemandeIntervention {

    public DemandeIntervention add(DemandeIntervention demandeIntervention);
    public DemandeIntervention update(DemandeIntervention demandeIntervention);
    public DemandeIntervention setAsDone(DemandeIntervention demandeIntervention);
    public void delete(DemandeIntervention demandeIntervention);
    public Boolean deleteById(Long id);
    public List<DemandeIntervention> findAllByVehiculeId(Long id);
    public List<DemandeIntervention> findAllByDate(LocalDate date);
    public List<DemandeIntervention> findAllByEtat(Boolean etat);
    public List<DemandeIntervention> findAll();
}
