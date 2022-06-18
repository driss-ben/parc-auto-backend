package com.gestion_parc.demo.controllers;
import com.gestion_parc.demo.beans.Assurance;
import com.gestion_parc.demo.beans.Assureur;
import com.gestion_parc.demo.services.implementations.AssureurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/assureurs")
public class AssureurController {

    @Autowired
    AssureurService assureurService;

    @PostMapping("/insert")
    public Object add(@RequestBody Assureur assureur){
        try {
            assureurService.add(assureur);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        assureurService.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public Assureur getById(@PathVariable Long id){return assureurService.findById(id);}

    @GetMapping("/nom/{nom}")
    public Assureur getByNom(@PathVariable String nom){return assureurService.findByNom(nom);}

    @GetMapping("")
    public List<Assureur> getAll(){
        return assureurService.findAll();
    }

    @PutMapping("/update")
    public Object update(@RequestBody Assureur assureur){
        try {
            assureurService.update(assureur);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }


}
