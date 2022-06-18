package com.gestion_parc.demo.services.implementations;


import com.gestion_parc.demo.beans.Huile;
import com.gestion_parc.demo.beans.Piece;
import com.gestion_parc.demo.beans.Pneu;
import com.gestion_parc.demo.repositories.HuileRepose;
import com.gestion_parc.demo.repositories.PieceRepos;
import com.gestion_parc.demo.repositories.PneuRepos;
import com.gestion_parc.demo.services.interfaces.IPiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PieceService implements IPiece {

    @Autowired
    PieceRepos pieceRepos;
    @Autowired
    HuileRepose huileRepose;
    @Autowired
    PneuRepos pneuRepos;

    @Override
    public Piece add(Piece piece) {
            Optional<Piece> pieceOptional = pieceRepos.findByNom(piece.getNom());
            if (!pieceOptional.isPresent()) pieceRepos.save(piece);
            else  throw new  IllegalStateException("Pieces already exist ");
        return piece;
    }

    @Override
    public Piece findById(Long id) {
        return pieceRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Piece findByNom(String nom) {
        return pieceRepos.findByNom(nom).stream().findFirst().orElse(null);
    }

    @Override
    public void delete(Piece piece) {
        pieceRepos.delete(piece);
    }

    @Override
    public void deleteById(Long id) {
        Piece piece = pieceRepos.findById(id).stream().findFirst().orElse(null);
        if (piece != null) {
            piece.setQuantiteStock(0L);
            piece.setCout(0.0);
            pieceRepos.save(piece);
        }
        else throw new IllegalStateException("il y a pas de piece avec id = "+id);
    }

    @Override
    public Piece update(Piece piece) {
        Optional<Piece> piece1 = pieceRepos.findById(piece.getId());
        if (! piece1.isPresent())
            throw new IllegalStateException("Piece n'existe pas !");
        else
            pieceRepos.save(piece);
        return piece;
    }

    @Override
    public List<Piece> findAll() {
        List<Piece> pieces = pieceRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<Pneu> pneus = pneuRepos.findAll();
        List<Huile> huiles = huileRepose.findAll();

        List<Piece> pieceList = new ArrayList<>();

        for (Piece piece: pieces ) {
            if (isPneu(piece, pneus)
                    || isHuile(piece, huiles))
                continue;
            else pieceList.add(piece);
        }
        return pieceList;
    }

    @Override
    public List<Piece> findAllPieces() {
        return  pieceRepos.findAll();
    }

    @Override
    public List<Piece> findAllPiecesStock() {
        List<Piece> pieces =  pieceRepos.findAllByQuantiteStockGreaterThan(0L);
        List<Pneu> pneus = pneuRepos.findAll();
        List<Huile> huiles = huileRepose.findAll();

        List<Piece> pieceList = new ArrayList<>();

        for (Piece piece: pieces ) {
            if (isPneu(piece, pneus)
                    || isHuile(piece, huiles))
                continue;
            else pieceList.add(piece);
        }
        return pieceList;
    }

    boolean isHuile(Piece piece, List<Huile> list){
         for (Huile huile : list ) {
             if (huile.getNom() == piece.getNom()) return  true;
         }
        return false;
    }
    boolean isPneu(Piece piece, List<Pneu> list){
        for (Pneu pneu : list ) {
            if (pneu.getNom() == piece.getNom()) return  true;
        }
        return false;
    }


}
