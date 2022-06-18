package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cout;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String territoire;

    @ManyToOne
    @JoinColumn(name = "vehicule")
    Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "assureur")
    Assureur assureur ;
}
