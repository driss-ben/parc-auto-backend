package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Piece;

import java.util.List;

public interface IPiece {


    Piece add(Piece piece);
    Piece findById(Long id);
    Piece findByNom(String nom);
    void delete(Piece piece);
    void deleteById(Long id);
    Piece update(Piece piece);
    List<Piece> findAll();
    List<Piece> findAllPieces();
    List<Piece> findAllPiecesStock();
}
