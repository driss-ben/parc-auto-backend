package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Huile;
import com.gestion_parc.demo.services.implementations.HuileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/huiles")
public class HuileController {

    @Autowired
    HuileService huileService;

    @PostMapping("/insert")
    public Object add(@RequestBody Huile huile){
        try {
             huileService.add(huile);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody Huile huile){

         try {
             huileService.update(huile);
             return true;
         }catch (Exception e){
             return e.getMessage();
         }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        huileService.deleteById(id);
    }

    @GetMapping("")
    public List<Huile> getAll(){
        return huileService.findAll();
    }

    @GetMapping("/stock")
    public List<Huile> getAllHuileStock(){
        return huileService.findAllHuileStock();
    }

    @GetMapping("/id/{id}")
    public Huile getById(@PathVariable Long id){
        return huileService.findById(id);
    }

    @GetMapping("/nom/{nom}")
    public Huile getByNom(@PathVariable String nom){
        return huileService.findByNom(nom);
    }


}
