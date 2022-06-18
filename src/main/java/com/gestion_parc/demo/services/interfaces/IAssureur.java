package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Assureur;

import java.util.List;

public interface IAssureur {

    public Assureur add(Assureur assureur);
    public Assureur update(Assureur assureur);
    public Assureur findById(Long id);
    public Assureur findByNom(String nom);
    public void deleteById(Long id);
    public void deleteByNom(String nom);
    public void delete(Assureur assureur);
    public List<Assureur> findAll();

}
