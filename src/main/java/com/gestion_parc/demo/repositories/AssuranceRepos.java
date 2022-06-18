package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Assurance;
import com.gestion_parc.demo.beans.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AssuranceRepos extends JpaRepository<Assurance,Long> {

    List<Assurance> findAllByDateFinBefore(Date date);

    @Query(value = "select a from Assurance a where a.vehicule=:vehicule and " +
            "a.dateFin >= ALL (select aa.dateFin from Assurance aa where aa.vehicule=:vehicule)")
     Assurance findLastByVehicule(@Param("vehicule") Vehicule vehicule);

     List<Assurance> findAllByVehiculeId(Long id);
     List<Assurance> findAllByVehiculeIdAndDateDebutAfter(Long id , LocalDate date);


}
