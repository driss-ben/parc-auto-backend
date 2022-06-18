package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Piece;
import com.gestion_parc.demo.beans.PieceDemandee;
import com.gestion_parc.demo.repositories.PiecesDemandeeRepos;
import com.gestion_parc.demo.repositories.PieceRepos;
import com.gestion_parc.demo.repositories.PieceUtiliseRepos;
import com.gestion_parc.demo.services.interfaces.IPieceDemandee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceDemandeeService implements IPieceDemandee {

    @Autowired
    PiecesDemandeeRepos pieceDemandeeRepos;

    @Autowired
    PieceRepos pieceRepos;

    @Autowired
    DemandePiecesService demandePiecesService;

    @Autowired
    PieceUtiliseRepos pieceUtiliseRepos;

    @Override
    public void add(PieceDemandee ...pieceDemandees) {
        for (PieceDemandee pieceDemandee: pieceDemandees) {
            Piece piece=pieceDemandee.getPiece();

            if(piece.getId() !=null){
                piece=pieceRepos.findById(piece.getId()).stream().findFirst().orElse(null);
            }
            else{
                pieceRepos.save(piece);
            }
            pieceDemandee.setPiece(piece);

            assert piece != null;
            pieceDemandeeRepos.save(pieceDemandee);
        }
    }

    @Override
    public void delete(PieceDemandee pieceDemandee) {
        pieceDemandeeRepos.delete(pieceDemandee);
    }

    @Override
    public void deleteById(Long id) {
        pieceDemandeeRepos.deleteById(id);
    }

    @Override
    public List<PieceDemandee> findAll() {
        return pieceDemandeeRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public PieceDemandee findById(Long id) {
        return pieceDemandeeRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public PieceDemandee update(PieceDemandee pieceDemandee) {
        return pieceDemandeeRepos.save(pieceDemandee);
    }
}
