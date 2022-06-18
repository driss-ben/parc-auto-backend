package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.beans.Prestataire;

import java.util.List;

public interface IPrestataire {

     Prestataire create(Prestataire prestataire);
     Prestataire findById(Long id);
     Prestataire findByNom(String nom);
     void delete(Prestataire prestataire);
     void deleteById(Long id);
     Prestataire update(Prestataire prestataire);
     List<Prestataire> findAll();

}
