package com.gestion_parc.demo.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected Double cout=0.0;

    @OneToOne
    @JoinColumn(name = "entretien")
    protected Entretien entretien;

}
