package com.gestion_parc.demo.controllers;


import com.gestion_parc.demo.beans.PieceDemandee;
import com.gestion_parc.demo.services.implementations.PieceDemandeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces-demandee")
@CrossOrigin( origins = "http://localhost:3000")
public class PiecesDemandeeController {

    @Autowired
    PieceDemandeeService piecesDemandeeService;

    @PostMapping("/insert")
    public Object add(@RequestBody PieceDemandee pieceDemandees){
        try {
            piecesDemandeeService.add(pieceDemandees);
            return true;
        }catch (Exception e) {
            return e.getMessage();
        }

    }

    @GetMapping("/{id}")
    public PieceDemandee getById(@PathVariable Long id){
        return  piecesDemandeeService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        piecesDemandeeService.deleteById(id);
    }

    @PutMapping("/update")
    public Object update(@RequestBody PieceDemandee pieceDemandee){
        try {
            piecesDemandeeService.update(pieceDemandee);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

}
