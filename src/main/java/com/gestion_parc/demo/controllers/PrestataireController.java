package com.gestion_parc.demo.controllers;


import com.gestion_parc.demo.beans.Prestataire;
import com.gestion_parc.demo.beans.ServiceFait;
import com.gestion_parc.demo.services.implementations.PrestataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/prestataires")
public class PrestataireController {

    @Autowired
    PrestataireService prestataireService;

    @PostMapping("/insert")
    public Object add(@RequestBody Prestataire prestataire){
        try {
            prestataireService.create(prestataire);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public Object  update(@RequestBody Prestataire prestataire){
        try {
            prestataireService.update(prestataire);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<Prestataire> getAll(){
        return prestataireService.findAll();
    }

    @GetMapping("/id/{id}")
    public Prestataire getById(@PathVariable Long id){
        return prestataireService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        prestataireService.deleteById(id);
    }

}
