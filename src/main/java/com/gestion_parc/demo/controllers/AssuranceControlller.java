package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Assurance;
import com.gestion_parc.demo.services.implementations.AssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/assurances")
public class AssuranceControlller {

    @Autowired
    AssuranceService assuranceService;

    @PostMapping("/insert")
    public Object add(@RequestBody Assurance assurance){
        try {
            assuranceService.add(assurance);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        assuranceService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public Assurance getById(@PathVariable Long id){return assuranceService.findById(id);}

    @PutMapping("/update")
    public Object update(@RequestBody Assurance assurance){
        try {
            assuranceService.update(assurance);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/vehicule/{id}")
    public Object getLastByVehicule(@PathVariable Long id){
        try {
            return assuranceService.findLastByVehicule(id);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("")
    public List<Assurance> getAll(){
        return assuranceService.findAll();
    }

    @GetMapping("/{id}")
    public List<Assurance> getAllByVehicule(@PathVariable Long id){
        return assuranceService.findAllByVehiculeId(id);
    }


}
