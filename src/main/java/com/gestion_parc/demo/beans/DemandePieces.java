package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class DemandePieces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date=LocalDate.now();
    private Boolean etat = false;

    @OneToOne
    @JoinColumn(name = "maintenance_interne")
    MaintenanceInterne maintenanceInterne ;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.MERGE})
    @JoinColumn(name = "demande_pieces")
    List<PieceDemandee> piecesDemandee;



}
