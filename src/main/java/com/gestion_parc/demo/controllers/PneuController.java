package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Pneu;
import com.gestion_parc.demo.services.implementations.PneuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pneus")
@CrossOrigin( origins = "http://localhost:3000")
public class PneuController {

    @Autowired
    PneuService pneuService;

    @PostMapping("/insert")
    public Object add(@RequestBody Pneu pneu){

        try {
             pneuService.add(pneu);
            return true;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }

    @PutMapping("/update")
    public Object update(@RequestBody Pneu pneu){
        try {
            pneuService.update(pneu);
            return true;
        }catch (Exception e){
            return e.getMessage() ;
        }
    }

    @GetMapping("")
    public List<Pneu> getAll(){
        return pneuService.findAll();
    }

    @GetMapping("/stock")
    public List<Pneu> getAllPneuStock(){
        return pneuService.findAllPneuStock();
    }

    @GetMapping("/id/{id}")
    public Pneu getById(@PathVariable Long id){
        return pneuService.findById(id);
    }

    @GetMapping("/nom/{nom}")
    public Pneu getByNom(@PathVariable String nom){
        return pneuService.findByNom(nom);
    }

    @GetMapping("/vehicule/{id}")
    public List<Pneu> getAllByRayon(@PathVariable Long id){
        return pneuService.findAllByRayon(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        pneuService.deleteById(id);
    }

}
