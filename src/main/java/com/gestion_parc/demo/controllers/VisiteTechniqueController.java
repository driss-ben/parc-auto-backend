package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.VisiteTechnique;
import com.gestion_parc.demo.services.implementations.VisiteTechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visite-technique")
@CrossOrigin( origins = "http://localhost:3000")
public class VisiteTechniqueController {

    @Autowired
    VisiteTechniqueService service;

    @PostMapping("/insert")
    public Object add(@RequestBody VisiteTechnique visiteTechnique){
        try {
            service.add(visiteTechnique);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/id/{id}")
    public Object getById(@PathVariable Long id){
        try {
            return service.findById(id);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<VisiteTechnique> getByAll(){
        return service.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable Long id){
        try {
            service.deleteById(id);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public Object update(@RequestBody VisiteTechnique visiteTechnique){
        try {
            service.update(visiteTechnique);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
