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
public class PieceAchete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateAchat=LocalDate.now();
    private Long quantite;
    private Double cout ;

    @ManyToOne
    @JoinColumn(name = "piece")
    Piece piece ;

    @ManyToOne
    @JoinColumn(name = "fournisseur")
    Fournisseur fournisseur ;

}
