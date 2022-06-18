package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.VisiteTechnique;

import java.util.List;

public interface IVisiteTechnique {
    void add(VisiteTechnique visiteTechnique);

    Object findById(Long id);

    void update(VisiteTechnique visiteTechnique);

    List<VisiteTechnique> findAll();
}
