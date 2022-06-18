package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Pneu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PneuRepos extends JpaRepository<Pneu , Long> {

    public Pneu findByNom(String nom);
    public void deleteByNom(String nom);
    public List<Pneu> findAllByQuantiteStockGreaterThan(Long quantity);
    public List<Pneu> findAllByQuantiteStockGreaterThanAndRayonEquals(Long quantity, Double rayon);
}
