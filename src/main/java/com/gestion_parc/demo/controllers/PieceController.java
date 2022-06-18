package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Piece;
import com.gestion_parc.demo.services.implementations.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
@CrossOrigin( origins = "http://localhost:3000")
public class PieceController {

    @Autowired
    PieceService pieceService;

    @PostMapping("/insert")
    public Object add(@RequestBody Piece piece){
        try {
            pieceService.add(piece);
            return true;
        }catch (Exception e){
           return e.getMessage();

        }

    }

    @GetMapping("")
    public List<Piece> getAll(){
        return pieceService.findAll();
    }

    @GetMapping("/all")
    public List<Piece> getAllPieces(){
        return pieceService.findAllPieces();
    }

    @GetMapping("/stock")
    public List<Piece> getAllPiecesStock(){
        return pieceService.findAllPiecesStock();
    }

    @GetMapping("/id/{id}")
    public Piece getById(@PathVariable Long id){
        return pieceService.findById(id);
    }

    @GetMapping("/nom/{nom}")
    public Piece getByNom(@PathVariable String nom){
        return pieceService.findByNom(nom);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            pieceService.deleteById(id);
            return true ;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody Piece piece){
        try {
            pieceService.update(piece);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
