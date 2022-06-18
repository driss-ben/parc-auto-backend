package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.PieceUtilise;

import java.util.List;

public interface IPieceUtilise {

    void add(PieceUtilise... piecesUtilise);
    void delete(PieceUtilise piecesUtilise);
    void deleteById(Long id);
    PieceUtilise findById(Long id);
    List<PieceUtilise> findAll();
    PieceUtilise update(PieceUtilise piecesUtilise);

}
