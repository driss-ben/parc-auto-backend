package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder

@Entity
@Table(name = "vehicule" ,
        uniqueConstraints = @UniqueConstraint(name = "immatriculation_unique",columnNames = "immatriculation"))
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String immatriculation;
    private String type;
    private String marque;
    private String gamme;
    private String modele;
    private String categoriePermis;
    private String numChassis;
    private String couleur;
    private LocalDate dateMiseCirculation;
    private LocalDate dateEntreeParc;
    private Double poidsVide;
    private Double capaciteReservoir;
    private String typeCarburant;
    private String etat;
    private Long kilometrage= Long.valueOf(0);
    private Double rayonRoue;



}
