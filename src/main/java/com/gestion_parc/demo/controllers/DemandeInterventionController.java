package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.DemandeIntervention;
import com.gestion_parc.demo.services.implementations.DemandeInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/demandes-intervention")
public class DemandeInterventionController {

    @Autowired
    DemandeInterventionService demandeInterventionService;

    @PostMapping("/insert")
    public Object add(@RequestBody DemandeIntervention demandeIntervention){
        try {
            demandeInterventionService.add(demandeIntervention);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody DemandeIntervention demandeIntervention){
        try {
            demandeInterventionService.update(demandeIntervention);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("")
    public List<DemandeIntervention> getAll(){
        return demandeInterventionService.findAll();
    }

    @GetMapping("/{id}")
    public DemandeIntervention getById(@PathVariable Long id){
        return demandeInterventionService.findById(id);
    }

    @GetMapping("/vehicule/{id}")
    public List<DemandeIntervention> getAllByVehicule(@PathVariable Long id){
        return demandeInterventionService.findAllByVehiculeId(id);
    }

    @GetMapping("/etat/{etat}")
    public List<DemandeIntervention> getAllByEtat(@PathVariable Boolean etat){
        return demandeInterventionService.findAllByEtat(etat);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            return demandeInterventionService.deleteById(id);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/done")
    public Object setAsDone(@RequestBody DemandeIntervention demandeIntervention){
        try {
            demandeInterventionService.setAsDone(demandeIntervention);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
