package com.gestion_parc.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class VisiteTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date ;

    @ManyToOne
    private  Vehicule vehicule;

    private Long kilometrage = 0l;

    private String agentTechnique;

    private Boolean phares = null;
    private Boolean feuxArrieres;
    private Boolean feuxFreinage;
    private Boolean lumiereSacGonflable;
    private Boolean signauxVirage;
    private Boolean instrumentsTableauBord;
    private Boolean eclairageInterierDome;
    private Boolean freins;
    private Boolean horn;
    private Boolean systemEchappement;
    private Boolean retroviseur;
    private Boolean essuisGlaces;
    private Boolean fenetreElectrique;
    private Boolean barreLumiereAeriene;
    private Boolean lumiereUrgenceInterieur;
    private Boolean spotLamp;
    private Boolean clinOeillePhares;
    private Boolean freinageSecours;
    private Boolean frontCornerStrobes;
    private Boolean arrowStrick;
    private Boolean claxon;
    private Boolean lampeEcrire;
    private Boolean sortie3trous;
    private Boolean boiteControl;
    private Boolean computer;
    private Boolean shotgunRack;
    private Boolean alleyLight;
    private Boolean videoSurveillance;
    private Boolean microphoneBox;
    private Boolean radioUrgences;



}
