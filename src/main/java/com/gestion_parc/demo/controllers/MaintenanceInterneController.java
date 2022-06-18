package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.MaintenanceInterne;
import com.gestion_parc.demo.services.implementations.MaintenanceInterneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/maintenances-interne")
public class MaintenanceInterneController {

    @Autowired
    MaintenanceInterneService maintenanceInterneService;

    @PostMapping("/insert")
    public  Object add(@RequestBody MaintenanceInterne maintenanceInterne){
        try {
            maintenanceInterneService.add(maintenanceInterne);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public  Object  update(@RequestBody MaintenanceInterne maintenanceInterne){
        try {
            maintenanceInterneService.update(maintenanceInterne);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/done/{id}")
    public Object setAsDone(@PathVariable Long id){
        try {
            maintenanceInterneService.setAsDone(id);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("")
    public List<MaintenanceInterne> getAll(){
        return maintenanceInterneService.findAll();
    }

    @GetMapping("/id/{id}")
    public  MaintenanceInterne getById(@PathVariable Long id){
        return maintenanceInterneService.findById(id);
    }

    @GetMapping("/vehicule/{id}")
    public List<MaintenanceInterne> getAllByVehicule(@PathVariable Long id){
        return maintenanceInterneService.findAllByVehicule(id);
    }

    @GetMapping("/not-done")
    public List<MaintenanceInterne> getAllNotDone(){
        return maintenanceInterneService.findAllNotDone();
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            return maintenanceInterneService.deleteById(id);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }


}
