package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Assurance;
import com.gestion_parc.demo.beans.ContratVehicule;

import java.util.Date;
import java.util.List;

public interface IContratVehicule {

    public List<ContratVehicule> findAllByType(String type);
    public ContratVehicule add(ContratVehicule contratVehicule);
    public ContratVehicule findById(Long id);
    public void deleteById(Long id);
    public ContratVehicule update(ContratVehicule contratVehicule);

    List<ContratVehicule> findAll();

    ContratVehicule findByVehiculeLastRecord(Long id);
}
