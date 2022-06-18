package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Statistique;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.services.implementations.StatistiqueService;
import com.gestion_parc.demo.services.implementations.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@CrossOrigin( origins = "http://localhost:3000")
public class VehiculeController {

    @Autowired
    VehiculeService vehiculeService;

    @Autowired
    StatistiqueService statistiqueService;

    @PostMapping("/insert")
    public Object add(@RequestBody Vehicule vehicule){
        try {
            vehiculeService.add(vehicule);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public Object update(@RequestBody Vehicule vehicule){
        try {
            vehiculeService.update(vehicule);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<Vehicule> getAll(){
        return vehiculeService.findAll();
    }

    @GetMapping("/without-carte-grise")
    public List<Vehicule> getAllWithoutCarteGrise(){
        return vehiculeService.findAllWithoutCarteGrise();
    }

    @GetMapping("/alert")
    public void getAllNeedsVidanges(){
         vehiculeService.findAllNeedsVidange();
         vehiculeService.findAllNeedsAssurance();
         vehiculeService.findAllNeedsPneumatique();
    }


    @GetMapping("/id/{id}")
    public Vehicule getById(@PathVariable Long id){
        return vehiculeService.findById(id);
    }

    @GetMapping("/immatriculation/{immatriculation}")
    public Vehicule getByImmatriculation(@PathVariable String immatriculation){
        return vehiculeService.findByImmatriculation(immatriculation);
    }

    @GetMapping("/statistiques/{id}")
    public List<Statistique> getStatistique(@PathVariable Long id){
        return statistiqueService.findByVehicule(id);
    }

    @GetMapping("/consommation/{id}")
    public Double getConsommation(@PathVariable Long id){
        return statistiqueService.findConsommationByVehicule(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        vehiculeService.deleteById(id);
    }

}
