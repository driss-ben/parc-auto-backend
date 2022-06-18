package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.MaintenanceExterne;

import java.time.LocalDate;
import java.util.List;


public interface IMaintenanceExterne {

    MaintenanceExterne add(MaintenanceExterne maintenanceExterne);
    void delete(MaintenanceExterne maintenanceExterne);
    Boolean deleteById(Long id);
    List<MaintenanceExterne> findAll();
    List<MaintenanceExterne> findAllByVehicule(Long id);
    List<MaintenanceExterne> findAllByVehiculeAndYear(Long id, LocalDate localDate);
    MaintenanceExterne findById(Long id);
    MaintenanceExterne update(MaintenanceExterne maintenanceExterne);

}
