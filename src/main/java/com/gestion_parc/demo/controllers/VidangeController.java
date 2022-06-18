package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Vidange;
import com.gestion_parc.demo.services.implementations.VidangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vidanges")
@CrossOrigin( origins = "http://localhost:3000")
public class VidangeController {

    @Autowired
    VidangeService vidangeService;

    @PostMapping("/insert")
    public Object add(@RequestBody Vidange vidange){
        try {
            vidangeService.add(vidange);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody Vidange vidange){
        try {
            vidangeService.update(vidange);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<Vidange> getAll(){
        return vidangeService.findAll();
    }

    @GetMapping("/{id}")
    public Vidange getById(@PathVariable Long id){
        return vidangeService.findById(id);
    }

    @GetMapping("/vehicule/{id}")
    public List<Vidange> getAllByVehicule(@PathVariable Long id){
        try {
            return vidangeService.findAllByVehicule(id);
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    @GetMapping("/huile/{id}")
    public List<Vidange> getAllByHuile(@PathVariable Long id){
        return vidangeService.findAllByHuile(id);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
           return vidangeService.deleteById(id);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

}
