package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.MaintenanceInterne;
import com.gestion_parc.demo.beans.Piece;
import com.gestion_parc.demo.beans.PieceUtilise;
import com.gestion_parc.demo.repositories.MaintenanceInterneRepos;
import com.gestion_parc.demo.repositories.PieceRepos;
import com.gestion_parc.demo.repositories.PieceUtiliseRepos;
import com.gestion_parc.demo.services.interfaces.IPieceUtilise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceUtiliseService implements IPieceUtilise {

    @Autowired
    PieceUtiliseRepos pieceUtiliseRepos;

    @Autowired
    PieceRepos pieceRepos;

    @Autowired
    MaintenanceInterneService maintenanceInterneService;

    @Override
    public void add(PieceUtilise... piecesUtilise) {
        for (PieceUtilise pieceUtilise: piecesUtilise ) {
            Piece piece =pieceUtilise.getPiece();
            if(piece.getId() != null){
                piece=pieceRepos.findById(piece.getId()).stream().findFirst().orElse(null);
            }
            else{
                pieceRepos.save(piece);
            }
            pieceUtilise.setPiece(piece);

            MaintenanceInterne maintenanceInterne=pieceUtilise.getMaintenanceInterne();
            if(maintenanceInterne.getId() != null){
                maintenanceInterne=maintenanceInterneService.findById(maintenanceInterne.getId());
            }
            else {
                maintenanceInterne=maintenanceInterneService.add(maintenanceInterne);
            }
            pieceUtilise.setMaintenanceInterne(maintenanceInterne);


            pieceUtilise.setCout(pieceUtilise.getQuantite() * piece.getCout());
            pieceUtiliseRepos.save(pieceUtilise);

        }
    }

    @Override
    public void delete(PieceUtilise piecesUtilise) {
        pieceUtiliseRepos.delete(piecesUtilise);
    }

    @Override
    public void deleteById(Long id) {
        pieceUtiliseRepos.deleteById(id);
    }

    @Override
    public PieceUtilise findById(Long id) {
        return pieceUtiliseRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public List<PieceUtilise> findAll() {
        return pieceUtiliseRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public PieceUtilise update(PieceUtilise piecesUtilise) {
        pieceUtiliseRepos.save(piecesUtilise);
        return piecesUtilise;
    }
}
