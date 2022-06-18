package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Statistique;

import java.util.List;

public interface IStatistique {

    public List<Statistique> findByVehicule(Long id);
    public Double findConsommationByVehicule(Long id);
}
