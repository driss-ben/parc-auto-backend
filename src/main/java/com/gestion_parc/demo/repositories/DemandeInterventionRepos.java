package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.DemandeIntervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DemandeInterventionRepos extends JpaRepository<DemandeIntervention,Long> {

    public List<DemandeIntervention> findAllByEtat(Boolean etat);
    public List<DemandeIntervention> findAllByDate(LocalDate date);
    public List<DemandeIntervention> findAllByVehiculeId(Long id);
}
