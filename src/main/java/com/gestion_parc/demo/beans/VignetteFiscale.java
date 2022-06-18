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
public class VignetteFiscale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montantPrincipal;
    private Double montantTsava;
    private Double penalite;
    private Double majoration;
    private LocalDate date=LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "vehicule")
    private Vehicule vehicule ;
}
