package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.CarteGrise;
import com.gestion_parc.demo.beans.ContratVehicule;
import com.gestion_parc.demo.services.implementations.CarteGriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/cartes-grise")
public class CarteGriseController {

    @Autowired
    CarteGriseService carteGriseService;

    @PostMapping("/insert")
    public Object add(@RequestBody CarteGrise carteGrise){
        try{
            carteGriseService.add(carteGrise);
            return true ;
        }catch (Exception e){
            return  e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody CarteGrise carteGrise){
        try{
            carteGriseService.update(carteGrise);
            return true;
        }catch (Exception e){
            return  e.getMessage();
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        carteGriseService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public CarteGrise getById(@PathVariable Long id){
        return carteGriseService.findById(id);
    }

    @GetMapping("/numeroserie/{numero}")
    public CarteGrise getById(@PathVariable String numero){
        return carteGriseService.findByNumeroSerie(numero);
    }

    @GetMapping("")
    public List<CarteGrise> getAll(){ return carteGriseService.findAll(); }

    @GetMapping("/vehicule/last/{id}")
    public CarteGrise findLast(@PathVariable Long id){
        return carteGriseService.findByVehiculeLastRecord(id) ;
    }
}
