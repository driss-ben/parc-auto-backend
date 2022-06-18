package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.beans.PieceAchete;
import com.gestion_parc.demo.services.implementations.PieceAcheteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces-achete")
@CrossOrigin( origins = "http://localhost:3000")
public class PiecesAcheteController {

    @Autowired
    PieceAcheteService pieceAcheteService;

    @PostMapping("/insert")
    public Object add(@RequestBody PieceAchete piecesAchete){
        try {
            pieceAcheteService.add(piecesAchete);
            return true;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }

    @PutMapping("/update")
    public Object update(@RequestBody PieceAchete piecesAchete){
        try {
            pieceAcheteService.update(piecesAchete);
            return true;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }

    @GetMapping("")
    public List<PieceAchete> getAll(){
        return pieceAcheteService.findAll();
    }

    @GetMapping("/id/{id}")
    public PieceAchete getById(@PathVariable Long id){
        return pieceAcheteService.findById(id);
    }

    @GetMapping("/fournisseur")
    public List<PieceAchete> getByFournisseur(@RequestBody Fournisseur fournisseur){
        return pieceAcheteService.findByfournisseur(fournisseur);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        pieceAcheteService.deleteById(id);
    }

}
