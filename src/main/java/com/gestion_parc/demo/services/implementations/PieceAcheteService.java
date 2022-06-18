package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.beans.Piece;
import com.gestion_parc.demo.beans.PieceAchete;
import com.gestion_parc.demo.repositories.FournisseurRepos;
import com.gestion_parc.demo.repositories.PieceAcheteRepos;
import com.gestion_parc.demo.repositories.PieceRepos;
import com.gestion_parc.demo.services.interfaces.IPieceAchete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceAcheteService implements IPieceAchete {

    @Autowired
    PieceAcheteRepos pieceAcheteRepos;

    @Autowired
    PieceRepos pieceRepos;

    @Autowired
    FournisseurRepos fournisseurRepos;

    @Override
    public PieceAchete findById(Long id) {
        return pieceAcheteRepos.getById(id);
    }

    @Override
    public PieceAchete add(PieceAchete piecesAchete) {
        if (piecesAchete.getPiece().getId()==null || piecesAchete.getFournisseur().getId()==null){
            throw new IllegalStateException("piece ou fournisseur n'est pas selectionnee. ");
        }else {
            Piece piece = piecesAchete.getPiece();
            if (piece.getId() != null) {
                piece = pieceRepos.findById(piece.getId()).stream().findFirst().orElse(null);
            } else {
                pieceRepos.save(piece);
            }
            piecesAchete.setPiece(piece);

            Fournisseur fournisseur = piecesAchete.getFournisseur();
            if (fournisseur.getId() != null) {
                fournisseur = fournisseurRepos.findById(fournisseur.getId()).
                        stream().findFirst().orElse(null);
            } else {
                fournisseurRepos.save(fournisseur);
            }
            piecesAchete.setFournisseur(fournisseur);

            pieceAcheteRepos.save(piecesAchete);

            Double oldCout = piece.getCout();
            Double newCout = piecesAchete.getCout();

            Long oldQte = piece.getQuantiteStock();
            Long newQte = piecesAchete.getQuantite();

            piece.setQuantiteStock(oldQte + newQte);

            piece.setCout((oldQte * oldCout + newQte * newCout) / (newQte + oldQte));

            pieceRepos.save(piece);

            return piecesAchete;
        }
    }

    @Override
    public void delete(PieceAchete piecesAchete) {
        pieceAcheteRepos.delete(piecesAchete);
    }

    @Override
    public void deleteById(Long id) {
        pieceAcheteRepos.deleteById(id);
    }

    @Override
    public List<PieceAchete> findAll() {
        return pieceAcheteRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<PieceAchete> findByfournisseur(Fournisseur fournisseur) {
        return pieceAcheteRepos.findAllByFournisseur(fournisseur);
    }

    @Override
    public PieceAchete update(PieceAchete piecesAchete) {
        PieceAchete pieceAchete=this.findById(piecesAchete.getId());
        if(pieceAchete != null){
            Piece piece=pieceAchete.getPiece();
            if(piece.getId() != null){
                piece=pieceRepos.findById(piece.getId()).stream().findFirst().orElse(null);

                Double oldCout = piece.getCout();
                Double coutToRemove = pieceAchete.getCout();

                Long oldQte = piece.getQuantiteStock();
                Long qteToRemove = pieceAchete.getQuantite();

                piece.setCout( (oldQte*oldCout - qteToRemove*coutToRemove)/(oldQte - qteToRemove));
                pieceRepos.save(piece);
            }
            pieceAchete.setPiece(piece);
            return this.add(piecesAchete);
        }
        else{
            // Doesnt exist exception
            return null;
        }
    }
}
