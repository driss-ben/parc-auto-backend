package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Pneu extends Piece{

    private Double largeur ;
    private Double hauteur ;
    private String radial ;
    private Double diametre ;
    private Double charge;
    private Long vitesse;
    private Long kilometrage;
    private Double rayon;
}
