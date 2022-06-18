package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class PieceUtilise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantite;
    private Double cout ;

    @ManyToOne
    @JoinColumn(name = "piece")
    Piece piece ;

    @ManyToOne
    @JoinColumn(name = "maintenance_interne")
    MaintenanceInterne maintenanceInterne;

}
