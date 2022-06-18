package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Huile;

import java.util.List;

public interface IHuile {

    public Huile add(Huile huile);
    public Huile update(Huile huile);
    public void delete(Huile huile);
    public void deleteById(Long id);
    public Huile findById(Long id);
    public Huile findByNom(String nom);
    public List<Huile> findAll();
    public List<Huile> findAllHuileStock();

}
