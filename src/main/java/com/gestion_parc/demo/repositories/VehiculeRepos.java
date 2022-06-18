package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepos extends JpaRepository<Vehicule,Long> {

    public Vehicule findByImmatriculation(String immatriculation);
    //public Vehicule findByContratVehiculeId(Long id);

}
