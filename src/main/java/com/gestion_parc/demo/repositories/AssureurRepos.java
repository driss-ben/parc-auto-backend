package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Assureur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssureurRepos extends JpaRepository<Assureur,Long> {

    public Assureur findByNom(String nom);
    public void deleteByNom(String nom);

}
