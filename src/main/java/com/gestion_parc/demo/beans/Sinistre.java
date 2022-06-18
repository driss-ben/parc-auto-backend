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
public class Sinistre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date=LocalDate.now();
    private String description;
    private String adresse;
    private Double coutMaintenance;
    private int pourcentageAssurer;

    @ManyToOne
    @JoinColumn(name = "vehicule")
    private Vehicule vehicule;

}
