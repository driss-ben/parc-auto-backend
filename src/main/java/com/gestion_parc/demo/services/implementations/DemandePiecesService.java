package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.DemandePieces;
import com.gestion_parc.demo.beans.MaintenanceInterne;
import com.gestion_parc.demo.beans.PieceDemandee;
import com.gestion_parc.demo.repositories.DemandePieceRepos;
import com.gestion_parc.demo.repositories.MaintenanceInterneRepos;
import com.gestion_parc.demo.repositories.PiecesDemandeeRepos;
import com.gestion_parc.demo.services.interfaces.IDemandePieces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandePiecesService implements IDemandePieces {

    @Autowired
    DemandePieceRepos demandePieceRepos;

    @Autowired
    PiecesDemandeeRepos piecesDemandeeRepos;

    @Autowired
    MaintenanceInterneRepos maintenanceInterneRepos;

    @Override
    public DemandePieces add(DemandePieces demandePieces) {
        if (demandePieces.getMaintenanceInterne().getId()==null){
            throw new IllegalStateException("Selectionner l'Id du Maintenances");
        }else {

            List<PieceDemandee> piecesDemandee = demandePieces.getPiecesDemandee();

            for (PieceDemandee pieceDemandee : piecesDemandee) {
                piecesDemandeeRepos.save(pieceDemandee);
            }
            demandePieces.setPiecesDemandee(piecesDemandee);
            demandePieceRepos.save(demandePieces);
            return demandePieces;
        }
    }

    @Override
    public void delete(DemandePieces demandePieces) {
        if(! demandePieces.getEtat()){
            List<PieceDemandee> pieceDemandees =demandePieces.getPiecesDemandee();

            for (PieceDemandee pieceDemandee : pieceDemandees ) {
                piecesDemandeeRepos.delete(pieceDemandee);
            }
            demandePieceRepos.delete(demandePieces);
        }
        else{
            // Exception
        }

    }

    @Override
    public void deleteById(Long id) {

        DemandePieces demandePieces=demandePieceRepos.findById(id).stream().findFirst().orElse(null);

        if (demandePieces != null){
            if(! demandePieces.getEtat()){
                List<PieceDemandee> pieceDemandees =demandePieces.getPiecesDemandee();
                for (PieceDemandee pieceDemandee : pieceDemandees ) {
                    piecesDemandeeRepos.delete(pieceDemandee);
                }
                demandePieceRepos.deleteById(id);
            }else {
                throw new IllegalStateException("vous ne pouvez pas supprimer une demande deja traitee");
            }
        }
    }

    @Override
    public DemandePieces SetAsDone(DemandePieces demandePieces){
        demandePieces.setEtat(true);
        return demandePieceRepos.save(demandePieces);
    }

    @Override
    public List<DemandePieces> findAll() {
        return demandePieceRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public DemandePieces findById(Long id) {
        return demandePieceRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public DemandePieces findByInterventionId(Long id) {
        return demandePieceRepos.findByMaintenanceInterneId(id);
    }

    @Override
    public DemandePieces update(DemandePieces demandePieces) {

        DemandePieces demande=this.findById(demandePieces.getId());
        if(demande != null){
            List<PieceDemandee> piecesDemandee = demandePieces.getPiecesDemandee();

            for (PieceDemandee pieceDemandee : piecesDemandee) {
                if (pieceDemandee.getId() != null){
                    piecesDemandeeRepos.save(pieceDemandee);
                }else {
                    int nbPiece = 0 ; // compteur si cette piece deja demandee
                    for (PieceDemandee p: demande.getPiecesDemandee()) {
                        if (pieceDemandee.getPiece().getId() == p.getPiece().getId()){
                            nbPiece++;
                        }
                    }
                    if (nbPiece != 0){
                        // si la piece est deja demandee
                        throw new IllegalStateException("Piece deja demandee, vous pouvez changee sa quantite");
                    }else {
                        // sinon
                        piecesDemandeeRepos.save(pieceDemandee);
                    }
                }

            }
            return demandePieceRepos.save(demandePieces);
        }
        else {
            // Doesnt exist exception
            return null;
        }
    }
}
