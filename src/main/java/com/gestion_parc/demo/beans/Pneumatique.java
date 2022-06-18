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
public class Pneumatique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Long kilometrage;
    private Double cout;
    private int nombrePneus ;
    private LocalDate date=LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "pneu")
    private Pneu pneu;

    @ManyToOne
    @JoinColumn(name = "vehicule")
    private Vehicule vehicule;

}
