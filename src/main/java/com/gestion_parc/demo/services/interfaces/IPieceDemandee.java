package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.PieceDemandee;

import java.util.List;

public interface IPieceDemandee {

    void add(PieceDemandee ...pieceDemandees);
    void delete(PieceDemandee pieceDemandee);


    void deleteById(Long id);
    List<PieceDemandee> findAll();
    PieceDemandee findById(Long id);
    PieceDemandee update(PieceDemandee pieceDemandee);

}
