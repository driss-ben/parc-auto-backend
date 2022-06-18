package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Sinistre;
import com.gestion_parc.demo.services.implementations.SinistreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sinistres")
@CrossOrigin( origins = "http://localhost:3000")
public class SinistreController {

    @Autowired
    SinistreService sinistreService;

    @PostMapping("/insert")
    public Object add(@RequestBody Sinistre sinistre){
        try {
            sinistreService.add(sinistre);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public Object update(@RequestBody Sinistre sinistre){
        try {
            sinistreService.update(sinistre);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<Sinistre> getAll(){
        return sinistreService.findAll();
    }

    @GetMapping("/{id}")
    public Sinistre getById(@PathVariable Long id){
        return sinistreService.findById(id);
    }

    @GetMapping("/vehicule/{id}")
    public List<Sinistre> getAllByVehicule(@PathVariable Long id){
        return sinistreService.findAllByVehiculeId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        sinistreService.deleteById(id);
    }

}
