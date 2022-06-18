package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Pneumatique;
import com.gestion_parc.demo.beans.Vehicule;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IPneumatique {

    public Pneumatique findById(Long id);
    public Pneumatique add(Pneumatique pneumatique);
    public Boolean update(Pneumatique pneumatique);
    public Boolean deleteById(Long id);
    public void delete(Pneumatique pneumatique);
    public List<Pneumatique> findAllByPneuId(Long id);
    public List<Pneumatique> findAllByVehiculeId(Long id);
    public List<Pneumatique> findAllByVehiculeIdAndYear(Long id , LocalDate localDate);
    public List<Pneumatique> findAll();
    public Pneumatique findLastByVehicule(Vehicule vehicule);

}
