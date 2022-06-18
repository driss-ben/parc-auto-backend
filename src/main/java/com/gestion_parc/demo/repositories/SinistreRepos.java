package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Sinistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SinistreRepos extends JpaRepository<Sinistre,Long> {

    List<Sinistre> findAllByVehiculeId(Long id);
    List<Sinistre> findAllByVehiculeIdAndDateAfter(Long id , LocalDate date);
    List<Sinistre> findAllByDate(LocalDate date);

}
