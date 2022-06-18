package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Vehicule;

import java.time.LocalDate;
import java.util.List;

public interface IVehicule {
    public Vehicule add(Vehicule vehicule);
    public Vehicule update(Vehicule vehicule);
    public Vehicule findById(Long id);
    public void deleteById(Long id);
    public Vehicule findByImmatriculation(String immatriculation);
    public List<Vehicule> findAll();
    public List<Vehicule> findAllWithoutCarteGrise();
    public List<Vehicule> findAllNeedsAssurance();
    public List<Vehicule> findAllNeedsVidange();
    public List<Vehicule> findAllNeedsPneumatique();
}
