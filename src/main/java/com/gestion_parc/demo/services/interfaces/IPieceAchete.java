package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.beans.PieceAchete;

import java.util.List;

public interface IPieceAchete {

    PieceAchete add(PieceAchete piecesAchete);
    void delete(PieceAchete piecesAchete);
    void deleteById(Long id);
    List<PieceAchete> findAll();
    PieceAchete findById(Long id);
    List<PieceAchete> findByfournisseur(Fournisseur fournisseur);
    PieceAchete update(PieceAchete piecesAchete);

}
