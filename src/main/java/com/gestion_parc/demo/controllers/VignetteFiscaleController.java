package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.VignetteFiscale;
import com.gestion_parc.demo.services.implementations.VignetteFiscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vignettes-fiscale")
@CrossOrigin( origins = "http://localhost:3000")
public class VignetteFiscaleController {

    @Autowired
    VignetteFiscaleService vignetteFiscaleService;

    @PostMapping("/insert")
    public Object add(@RequestBody VignetteFiscale vignetteFiscale){
        try {
            vignetteFiscaleService.add(vignetteFiscale);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/update")
    public Object update(@RequestBody VignetteFiscale vignetteFiscale){
        try {
            vignetteFiscaleService.update(vignetteFiscale);
            return true;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("")
    public List<VignetteFiscale> getAll(){
        return vignetteFiscaleService.findAll();
    }

    @GetMapping("/vehicule/{id}")
    public List<VignetteFiscale> getAllByVehicule(@PathVariable Long id){
        return vignetteFiscaleService.findAllByVehicule(id);
    }

    @GetMapping("/id/{id}")
    public VignetteFiscale getById(@PathVariable Long id){
        return vignetteFiscaleService.findById(id);
    }

}
