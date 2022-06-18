package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data

@Entity
public class MaintenanceExterne extends Intervention{

    private String garage;

    @OneToOne
    @JoinColumn(name = "prestataire")
    private Prestataire prestataire ;

    @OneToOne
    @JoinColumn(name = "service_fait")
    private ServiceFait serviceFait ;

    @OneToOne
    @JoinColumn(name = "engagement")
    private Engagement engagement ;

}
