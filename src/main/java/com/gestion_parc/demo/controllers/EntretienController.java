package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Entretien;
import com.gestion_parc.demo.services.implementations.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/entretiens")
public class EntretienController {

    @Autowired
    EntretienService entretienService;

    @PostMapping("/insert")
    public Object add(@RequestBody Entretien entretien){
        try {
            entretienService.add(entretien);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public Object update(@RequestBody Entretien entretien){
        try {
            entretienService.update(entretien);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("")
    public List<Entretien> getAll(){
        return entretienService.findAll();
    }

    @GetMapping("/type-intervention/{type}")
    public List<Entretien> getAllByType(@PathVariable String type){
        return entretienService.findAllByTypeIntervention(type);
    }

    @GetMapping("/type-and-etat/{type}")
    public List<Entretien> getAllByTypeAndEtat(@PathVariable String type){
        return entretienService.findAllByTypeInterventionAndEtat(type);
    }

    @GetMapping("/vehicule/{id}")
    public List<Entretien> getAllByVehiclule(@PathVariable Long id){
        return entretienService.findAllByVehiculeId(id);
    }

    @GetMapping("/demande-intervention/{id}")
    public Entretien getByIntervention(@PathVariable Long id){
        return entretienService.findByDemandeInterventionId(id);
    }

    @GetMapping("/id/{id}")
    public Entretien getById(@PathVariable Long id){
        return entretienService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            return entretienService.deleteById(id);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

}
