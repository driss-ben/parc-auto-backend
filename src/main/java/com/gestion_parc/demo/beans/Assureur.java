package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "nom_unique" , columnNames = "nom"))
public class Assureur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom ;
    private String telephone ;
    private String email ;
    private String adresse ;
    private String ville ;

}
