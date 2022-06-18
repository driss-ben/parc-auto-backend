package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.*;
import com.gestion_parc.demo.repositories.*;
import com.gestion_parc.demo.services.interfaces.IMaintenanceInterne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service

public class MaintenanceInterneService implements IMaintenanceInterne {

    @Autowired
    MaintenanceInterneRepos maintenanceInterneRepos;

    @Autowired
    EntretienService entretienService;

    @Autowired
    EntretienRepos entretienRepos;

    @Autowired
    PieceUtiliseRepos pieceUtiliseRepos;

    @Autowired
    DemandePieceRepos demandePieceRepos;

    @Autowired
    PieceRepos pieceRepos;

    @Autowired
    PiecesDemandeeRepos piecesDemandeeRepos;

    @Autowired
    DemandeInterventionRepos demandeInterventionRepos;

    @Override
    public MaintenanceInterne add(MaintenanceInterne maintenanceInterne) {
        if (maintenanceInterne.getEntretien().getId()==null){
            throw new IllegalStateException("Selectionner l'entretien convenable");
        }else {
            Entretien entretien = maintenanceInterne.getEntretien();
            if (entretien.getId() != null) {
                entretien = entretienService.findById(entretien.getId());
            } else {
                entretien = entretienService.add(entretien);
            }
            entretien.setEtat(true);
            entretienRepos.save(entretien);
            maintenanceInterne.setEntretien(entretien);

            maintenanceInterneRepos.save(maintenanceInterne);
            return maintenanceInterne;
        }
    }

    @Override
    public void delete(MaintenanceInterne maintenanceInterne) {
        maintenanceInterneRepos.delete(maintenanceInterne);
    }

    @Override
    public Boolean deleteById(Long id) {
        MaintenanceInterne maintenanceInterne=maintenanceInterneRepos.findById(id).stream().findFirst().orElse(null);
        if(maintenanceInterne != null){
            if(! maintenanceInterne.getEtat()){
                Entretien entretien=maintenanceInterne.getEntretien();
                entretien.setEtat(false);
                try {
                    entretienRepos.save(entretien);
                    DemandePieces demandePieces = demandePieceRepos.findByMaintenanceInterneId(maintenanceInterne.getId());
                    if (demandePieces != null) {
                        List<PieceDemandee> pieceDemandee = demandePieces.getPiecesDemandee();
                        for (PieceDemandee p: pieceDemandee
                             ) {
                            piecesDemandeeRepos.delete(p);
                        }

                    }
                    demandePieceRepos.delete(demandePieces);
                    maintenanceInterneRepos.deleteById(id);
                    return true;
                }catch (Exception e){
                    throw new IllegalStateException("Erreur lors de la suppression !");
                }
            }
            throw new IllegalStateException("Vous ne pouvez pas supprimer une maintenance traitee !");
        }
        throw new IllegalStateException("La maintenance n'existe pas !");
    }

    @Override
    public List<MaintenanceInterne> findAll() {
        return maintenanceInterneRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<MaintenanceInterne> findAllByVehicule(Long id) {
        return maintenanceInterneRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<MaintenanceInterne> findAllByVehiculeAndYear(Long id , LocalDate localDate) {
        return maintenanceInterneRepos.findAllByVehiculeIdAndDateFinAfter(id,localDate);
    }

    @Override
    public MaintenanceInterne findById(Long id) {
        return maintenanceInterneRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public MaintenanceInterne update(MaintenanceInterne maintenanceInterne) {
        maintenanceInterneRepos.save(maintenanceInterne);
        return maintenanceInterne;
    }

    @Override
    public Boolean setAsDone(Long id) {
        Double cost=0.0;
        MaintenanceInterne maintenanceInterne=this.findById(id);
        if(!maintenanceInterne.getEtat()) {
            DemandePieces demandePieces = demandePieceRepos.findByMaintenanceInterneId(id);
            //List<PieceUtilise> pieceUtilises = pieceUtiliseRepos.findAllByMaintenanceInterneId(maintenanceInterne.getId());
            if (demandePieces != null) {
                List<PieceDemandee> pieceDemandees = demandePieces.getPiecesDemandee();

                for (PieceDemandee pd: pieceDemandees) {
                    Piece piece = pd.getPiece();
                    if (piece.getQuantiteStock() < pd.getQuantite()){
                        throw new IllegalStateException("quelque pieces sont de quantites unsuffisantes dans le stock.");
                    }
                }

                //Tout les quantites des pieces sont suffisantes ==> Utiliser tout les pieces

                for (PieceDemandee pieceDemandee : pieceDemandees) {
                    Piece piece = pieceDemandee.getPiece();

                        PieceUtilise pieceUtilise = new PieceUtilise();
                        pieceUtilise.setPiece(piece);
                        pieceUtilise.setMaintenanceInterne(maintenanceInterne);
                        pieceUtilise.setQuantite(pieceDemandee.getQuantite());
                        pieceUtilise.setCout(pieceDemandee.getQuantite() * piece.getCout());
                        pieceUtiliseRepos.save(pieceUtilise);
                        piece.setQuantiteStock(piece.getQuantiteStock() - pieceDemandee.getQuantite());
                        pieceRepos.save(piece);
                        cost += pieceUtilise.getCout();

                }

                maintenanceInterne.setEtat(true);
                LocalDate date = LocalDate.now();
                maintenanceInterne.setDateFin(date);
                demandePieces.setEtat(true);
                demandePieceRepos.save(demandePieces);
                maintenanceInterne.setCout(cost);
                maintenanceInterneRepos.save(maintenanceInterne);
                return true;

            }else {
                maintenanceInterne.setEtat(true);
                maintenanceInterneRepos.save(maintenanceInterne);
//                throw new IllegalStateException("La M.Interne n'existe pas !");
            }

        }
        return null;
    }

    @Override
    public List<MaintenanceInterne> findAllNotDone() {
        List<MaintenanceInterne> maintenanceInternes = maintenanceInterneRepos.findAllByEtatEquals(false);

        List<MaintenanceInterne> list = new ArrayList<>();

        for (MaintenanceInterne m: maintenanceInternes ) {
            DemandePieces demandePieces = demandePieceRepos.findByMaintenanceInterneId(m.getId());
            if (demandePieces == null) list.add(m);
        }

        return list;
    }
}
