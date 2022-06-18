package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Pneu;

import java.util.List;

public interface IPneu {

    Pneu findByNom(String nom);
    void deleteByNom(String nom);
    Pneu update(Pneu pneu);
    Pneu findById(Long id);
    void deleteById(Long id);
    Pneu add(Pneu pneu);
    List<Pneu> findAll();
    List<Pneu> findAllPneuStock();
    List<Pneu> findAllByRayon(Long id);

}
