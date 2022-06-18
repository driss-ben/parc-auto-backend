package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Huile;
import com.gestion_parc.demo.beans.TypeHuile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuileRepose extends JpaRepository<Huile,Long> {

    public Huile findByNom(String nom);
    public List<Huile> findAllByTypeHuile(TypeHuile typeHuile);
    public List<Huile> findAllByQuantiteStockGreaterThan(Long quantite);

}
