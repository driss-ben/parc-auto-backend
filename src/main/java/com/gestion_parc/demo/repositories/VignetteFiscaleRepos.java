package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.beans.VignetteFiscale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VignetteFiscaleRepos extends JpaRepository<VignetteFiscale,Long> {

    List<VignetteFiscale> findAllByVehiculeId(Long id);

    @Query(value = "select v from VignetteFiscale v where v.vehicule=:vehicule and " +
            "v.id>=all (select vv.id from VignetteFiscale vv where vv.vehicule=:vehicule)")
    public VignetteFiscale findLastByVehicule(@Param("vehicule") Vehicule vehicule);
    public List<VignetteFiscale> findAllByVehiculeIdAndDateAfter(Long id , LocalDate date);

}
