package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Engagement;
import com.gestion_parc.demo.beans.Fournisseur;

import java.util.List;

public interface IEngagement {

    public Engagement add(Engagement engagement);
    public Engagement findById(Long id);
    public void delete(Engagement engagement);
    public void deleteById(Long id);
    public Engagement update(Engagement engagement);
    public List<Engagement> findAll();
    
}
