package com.gestion_parc.demo.controllers;


import com.gestion_parc.demo.beans.MaintenanceExterne;
import com.gestion_parc.demo.services.implementations.MaintenanceExterneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/maintenances-externe")
public class MaintenanceExterneController {

    @Autowired
    MaintenanceExterneService maintenanceExterneService;

    @PostMapping("/insert")
    public  Object  add(@RequestBody MaintenanceExterne maintenanceExterne){
        try {
            maintenanceExterneService.add(maintenanceExterne);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public Object update(@RequestBody  MaintenanceExterne maintenanceExterne ){
        try {
            maintenanceExterneService.update(maintenanceExterne);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<MaintenanceExterne> getAll(){
        return maintenanceExterneService.findAll();
    }

    @GetMapping("/id/{id}")
    public MaintenanceExterne getById(@PathVariable Long id){
        return maintenanceExterneService.findById(id);
    }

    @GetMapping("/vehicule/{id}")
    public List<MaintenanceExterne> getAllByVehicule(@PathVariable Long id){
        return maintenanceExterneService.findAllByVehicule(id);
    }
    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            return maintenanceExterneService.deleteById(id);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }



}
