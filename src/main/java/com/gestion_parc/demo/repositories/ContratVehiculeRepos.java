package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.ContratVehicule;
import com.gestion_parc.demo.beans.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratVehiculeRepos extends JpaRepository<ContratVehicule , Long> {

     List<ContratVehicule> findAllByTypeEquals(String type);


}
