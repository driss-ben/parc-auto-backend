package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.ContratVehicule;
import com.gestion_parc.demo.services.implementations.ContratVehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@CrossOrigin( origins = "http://localhost:3000")
public class ContratController {

    @Autowired
    ContratVehiculeService contratVehiculeService;

    @PostMapping("insert")
    public Object add(@RequestBody ContratVehicule contratVehicule){

        try {
            contratVehiculeService.add(contratVehicule);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/id/{id}")
    public ContratVehicule getById(@PathVariable Long id){
        return contratVehiculeService.findById(id);
    }

    @GetMapping("")
    public List<ContratVehicule> findAll(){
        return contratVehiculeService.findAll() ;
    }

    @GetMapping("/vehicule/last/{id}")
    public ContratVehicule findLast(@PathVariable Long id){
        return contratVehiculeService.findByVehiculeLastRecord(id) ;
    }
}
