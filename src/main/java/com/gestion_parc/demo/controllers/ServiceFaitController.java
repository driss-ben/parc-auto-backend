package com.gestion_parc.demo.controllers;


import com.gestion_parc.demo.beans.Engagement;
import com.gestion_parc.demo.beans.ServiceFait;
import com.gestion_parc.demo.services.implementations.ServiceFaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services-fait")
@CrossOrigin( origins = "http://localhost:3000")
public class ServiceFaitController {

    @Autowired
    ServiceFaitService serviceFaitService;

    @PostMapping("/insert")
    public ServiceFait add(@RequestBody ServiceFait serviceFait){
        try {
            serviceFaitService.create(serviceFait);
        }catch (Exception e){
            return null;
        }
        return serviceFait;
    }

    @PutMapping("/update")
    public ServiceFait update(@RequestBody ServiceFait serviceFait){
        return serviceFaitService.update(serviceFait);
    }

    @GetMapping("")
    public List<ServiceFait> getAll(){
        return serviceFaitService.findAll();
    }

    @GetMapping("/id/{id}")
    public ServiceFait getById(@PathVariable Long id){
        return serviceFaitService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        serviceFaitService.deleteById(id);
    }

}
