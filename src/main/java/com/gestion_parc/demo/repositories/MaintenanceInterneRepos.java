package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.MaintenanceInterne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceInterneRepos extends JpaRepository<MaintenanceInterne,Long> {

    @Query(value = "select m from MaintenanceInterne m where m.entretien.demandeIntervention.vehicule.id=:id and " +
            "m.dateFin >=:date")
    public List<MaintenanceInterne> findAllByVehiculeIdAndDateFinAfter(@Param("id") Long id , @Param("date") LocalDate date );

    @Query(value = "select m from MaintenanceInterne  m where m.entretien.demandeIntervention.vehicule.id=:id")
    public List<MaintenanceInterne> findAllByVehiculeId(@Param("id") Long id);

    public List<MaintenanceInterne> findAllByEtatEquals(Boolean etat);
}
