package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FournisseurRepos extends JpaRepository<Fournisseur, Long> {

    Fournisseur findByNom(String nom);

    Fournisseur findByEmail(String email);
}
