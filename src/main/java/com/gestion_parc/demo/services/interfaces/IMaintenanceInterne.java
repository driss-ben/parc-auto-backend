package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.MaintenanceInterne;
import com.gestion_parc.demo.beans.PieceDemandee;

import java.time.LocalDate;
import java.util.List;

public interface IMaintenanceInterne {

    MaintenanceInterne add(MaintenanceInterne maintenanceInterne);
    void delete(MaintenanceInterne maintenanceInterne);
    Boolean deleteById(Long id);
    List<MaintenanceInterne> findAll();
    List<MaintenanceInterne> findAllByVehicule(Long id);
    List<MaintenanceInterne> findAllByVehiculeAndYear(Long id, LocalDate localDate);
    MaintenanceInterne findById(Long id);
    MaintenanceInterne update(MaintenanceInterne maintenanceInterne);
    Boolean setAsDone(Long id);
    List<MaintenanceInterne> findAllNotDone();

}
