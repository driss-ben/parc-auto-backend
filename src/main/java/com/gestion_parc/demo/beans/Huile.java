package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Huile extends Piece{


    private Long kilometrage;

    @ManyToOne
    @JoinColumn(name = "type_huile")
    private TypeHuile typeHuile;

}
