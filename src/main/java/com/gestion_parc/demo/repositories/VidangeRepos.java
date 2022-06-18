package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.beans.Vidange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface VidangeRepos extends JpaRepository<Vidange,Long> {

    public List<Vidange> findAllByDate(LocalDate date);
    public List<Vidange> findAllByVehiculeId(Long id);
    public List<Vidange> findAllByVehiculeIdAndDateAfter(Long id,LocalDate date);
    public List<Vidange> findAllByHuileId(Long id);

    @Query(value = "select v from Vidange v where v.vehicule=:vehicule and " +
                   "v.id>=all (select vv.id from Vidange vv where vv.vehicule=:vehicule)")
    public Vidange findLastByVehicule(@Param("vehicule") Vehicule vehicule);



}
