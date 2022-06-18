package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ContratVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numContrat;
    private String type;
    private LocalDate dateContrat;
    private LocalDate dateEcheance;
    private Double montant;

    @OneToOne
    private Vehicule vehicule;

}
