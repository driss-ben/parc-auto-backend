package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.CarteGrise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarteGriseRepos extends JpaRepository<CarteGrise,Long> {

    CarteGrise findByNumeroSerie(String numero);
    List<CarteGrise> findAllByDateOperation(LocalDate date);
    List<CarteGrise> findAllByFinValidite(LocalDate date);
    CarteGrise findByProprietaire(String proprietaire);

    CarteGrise findByVehiculeId(Long id);
}
