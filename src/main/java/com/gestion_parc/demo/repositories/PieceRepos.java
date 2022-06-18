package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PieceRepos extends JpaRepository<Piece,Long> {

    Optional<Piece> findByNom(String nom);
    List<Piece> findAllByQuantiteStockGreaterThan(Long quantite);

}
