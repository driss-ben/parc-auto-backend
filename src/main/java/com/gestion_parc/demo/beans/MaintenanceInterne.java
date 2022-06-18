package com.gestion_parc.demo.beans;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data


@Entity
public class MaintenanceInterne extends Intervention{

    private String adresse;
    private Boolean etat=false;

}
