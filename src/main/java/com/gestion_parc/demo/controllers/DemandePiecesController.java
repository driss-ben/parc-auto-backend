package com.gestion_parc.demo.controllers;


import com.gestion_parc.demo.beans.DemandePieces;
import com.gestion_parc.demo.services.implementations.DemandePiecesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/demandes-pieces")
public class DemandePiecesController {

    @Autowired
    DemandePiecesService demandePiecesService;

    @PostMapping("/insert")
    public Object  add(@RequestBody DemandePieces demandePieces){
        try {
            demandePiecesService.add(demandePieces);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
        
    }

    @GetMapping("")
    public List<DemandePieces> getAll(){
        return demandePiecesService.findAll();
    }

    @GetMapping("/id/{id}")
    public DemandePieces  getById(@PathVariable Long id){
        return demandePiecesService.findById(id);
    }

    @GetMapping("/intervention/{id}")
    public DemandePieces getByIntervetion(@PathVariable Long id){
        return demandePiecesService.findByInterventionId(id);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            demandePiecesService.deleteById(id);
            return true;
        }catch (Exception e) {
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody DemandePieces demandePieces){
        try {
            demandePiecesService.update(demandePieces);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/done")
    public DemandePieces setDone(@RequestBody DemandePieces demandePieces){
        return demandePiecesService.SetAsDone(demandePieces);
    }
}
