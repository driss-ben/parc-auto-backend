package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Fournisseur;

import java.util.List;

public interface IFournisseur {

    public Fournisseur add(Fournisseur fournisseur);
    public Fournisseur findById(Long id);
    public Fournisseur findByNom(String nom);
    public void delete(Fournisseur fournisseur);
    public void deleteById(Long id);
    public Fournisseur update(Fournisseur fournisseur);
    public List<Fournisseur> findAll();

}
