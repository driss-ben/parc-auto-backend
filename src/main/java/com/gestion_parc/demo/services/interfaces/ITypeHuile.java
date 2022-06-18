package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.TypeHuile;

import java.util.List;

public interface ITypeHuile {

    List<TypeHuile> findAll();
    TypeHuile findById(Long id);
    TypeHuile findByNom(String nom);
    Object add(TypeHuile typeHuile);
    void delete(TypeHuile typeHuile);
    void deleteById(Long id);
    void deleteByNom(String nom);
    TypeHuile update(TypeHuile typeHuile);


}
