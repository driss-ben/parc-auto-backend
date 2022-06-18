package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestataireRepos  extends JpaRepository<Prestataire,Long> {

    Prestataire findByNom(String name);

}
