package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.beans.Piece;
import com.gestion_parc.demo.beans.PieceAchete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceAcheteRepos extends JpaRepository<PieceAchete,Long> {

    List<PieceAchete> findAllByFournisseur(Fournisseur fournisseur);

}
