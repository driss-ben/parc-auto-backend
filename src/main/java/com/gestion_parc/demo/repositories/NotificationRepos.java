package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Notification;
import com.gestion_parc.demo.beans.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificationRepos extends JpaRepository<Notification,Long> {
    Notification findByVehiculeAndKilometrageActuelAndTitle(Vehicule vehicule,Long kl,String title);
    Notification findByVehiculeAndDateAndTitle(Vehicule vehicule, LocalDate date, String title);
    List<Notification> findByIsRead(Boolean read);
}
