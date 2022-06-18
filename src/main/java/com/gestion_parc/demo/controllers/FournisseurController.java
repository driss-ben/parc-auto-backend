package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.services.implementations.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    FournisseurService fournisseurService;

    @PostMapping("/insert")
    public Object add(@RequestBody Fournisseur fournisseur){
        try {
            fournisseurService.add(fournisseur);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<Fournisseur> getAll(){
        return fournisseurService.findAll();
    }

    @GetMapping("/id/{id}")
    public Fournisseur getById(@PathVariable Long id){
        return fournisseurService.findById(id);
    }

    @GetMapping("/nom/{nom}")
    public Fournisseur getByNom(@PathVariable String nom){
        return fournisseurService.findByNom(nom);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        fournisseurService.deleteById(id);
    }

    @PutMapping("/update")
    public Object update(@RequestBody Fournisseur fournisseur){
        try {
            fournisseurService.update(fournisseur);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
