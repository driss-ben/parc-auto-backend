package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Pneumatique;
import com.gestion_parc.demo.beans.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PneumatiqueRepos extends JpaRepository<Pneumatique,Long> {

    @Query(value = "select p from Pneumatique p where p.vehicule=:vehicule and " +
            "p.id>=all (select pp.id from Pneumatique pp where pp.vehicule=:vehicule)")
    public Pneumatique findLastByVehicule(@Param("vehicule") Vehicule vehicule);

    public List<Pneumatique> findAllByPneuId(Long id);
    public List<Pneumatique> findAllByVehiculeId(Long id);
    public List<Pneumatique> findAllByVehiculeIdAndDateAfter(Long id, LocalDate date);
}
