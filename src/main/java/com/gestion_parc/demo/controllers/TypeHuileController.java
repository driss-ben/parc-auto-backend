package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.TypeHuile;
import com.gestion_parc.demo.services.implementations.TypeHuileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types-huile")
@CrossOrigin( origins = "http://localhost:3000")
public class TypeHuileController {

    @Autowired
    TypeHuileService typeHuileService;

    @PostMapping("/insert")
    public Object add(@RequestBody TypeHuile typeHuile){
        try{
            return typeHuileService.add(typeHuile);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/update")
    public TypeHuile update(@RequestBody TypeHuile typeHuile){
        return typeHuileService.update(typeHuile);
    }

    @GetMapping("")
    public List<TypeHuile> getAll(){
        return typeHuileService.findAll();
    }

    @GetMapping("/nom/{nom}")
    public TypeHuile getByNom(@PathVariable String nom){
        return typeHuileService.findByNom(nom);
    }

    @GetMapping("/id/{id}")
    public TypeHuile getById(@PathVariable Long id){
        return typeHuileService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        typeHuileService.deleteById(id);
    }

}
