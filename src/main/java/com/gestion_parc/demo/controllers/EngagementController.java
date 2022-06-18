package com.gestion_parc.demo.controllers;


import com.gestion_parc.demo.beans.Engagement;
import com.gestion_parc.demo.services.implementations.EngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/engagements")
public class EngagementController {

    @Autowired
    EngagementService engagementService;

    @PostMapping("/insert")
    public Object add(@RequestBody Engagement engagement){
        try {
            engagementService.add(engagement);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<Engagement> getAll(){
        return engagementService.findAll();
    }

    @GetMapping("/id/{id}")
    public Engagement getById(@PathVariable Long id){
        return engagementService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        engagementService.deleteById(id);
    }

    @PutMapping("/update")
    public Object  update(@RequestBody Engagement engagement){
        try {
            engagementService.update(engagement);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }

    }

}
