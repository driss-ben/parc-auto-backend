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
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeIntervention;
    private String lieu;
    private Boolean etat = false;
    private LocalDate date=LocalDate.now();

    @OneToOne
    @JoinColumn(name = "demande_intervention")
    private DemandeIntervention demandeIntervention ;

}
