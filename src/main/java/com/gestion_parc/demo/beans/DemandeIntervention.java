package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class DemandeIntervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date=LocalDate.now();
    private String indexHoraire;
    private Boolean etat = false;
    private String description ;

    @ManyToOne
    @JoinColumn(name = "vehicule")
    private Vehicule vehicule ;

}
