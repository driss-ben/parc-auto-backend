package com.gestion_parc.demo.controllers;


import com.gestion_parc.demo.beans.PieceUtilise;
import com.gestion_parc.demo.services.implementations.PieceUtiliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces-utilise")
@CrossOrigin( origins = "http://localhost:3000")
public class PiecesUtiliseController {

    @Autowired
    PieceUtiliseService piecesUtiliseService;

    @PostMapping("/insert")
    public void add(@RequestBody PieceUtilise...piecesUtilises){
        try {
            piecesUtiliseService.add(piecesUtilises);
        }catch (Exception e){
//            return null;
        }
//        return piecesUtilise;
    }

    @PutMapping("/update")
    public PieceUtilise update(@RequestBody PieceUtilise piecesUtilise){
        return piecesUtiliseService.update(piecesUtilise);
    }

    @GetMapping("")
    public List<PieceUtilise> getAll(){
        return piecesUtiliseService.findAll();
    }

    @GetMapping("/id/{id}")
    public PieceUtilise getById(@PathVariable Long id){
        return piecesUtiliseService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        piecesUtiliseService.deleteById(id);
    }

}
