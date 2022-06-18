package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Pneumatique;
import com.gestion_parc.demo.services.implementations.PneumatiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pneumatiques")
@CrossOrigin( origins = "http://localhost:3000")
public class PneumatiqueController {

    @Autowired
    PneumatiqueService pneumatiqueService;

    @PostMapping("/insert")
    public Object add(@RequestBody Pneumatique pneumatique){
        try {
             pneumatiqueService.add(pneumatique);
             return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody Pneumatique pneumatique){
        try {
            return pneumatiqueService.update(pneumatique);
        }catch (Exception e){
            return e.getMessage() ;
        }
    }

    @GetMapping("")
    public List<Pneumatique> getAll(){
        return pneumatiqueService.findAll();
    }

    @GetMapping("/vehicule/{id}")
    public List<Pneumatique> getAllByVehicule(@PathVariable Long id){
        return pneumatiqueService.findAllByVehiculeId(id);
    }

    @GetMapping("/{id}")
    public Pneumatique getById(@PathVariable Long id){
        return pneumatiqueService.findById(id);
    }

    @GetMapping("/pneu/{id}")
    public List<Pneumatique> getAllByPneu(@PathVariable Long id){
        return pneumatiqueService.findAllByPneuId(id);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            return pneumatiqueService.deleteById(id);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }


}
