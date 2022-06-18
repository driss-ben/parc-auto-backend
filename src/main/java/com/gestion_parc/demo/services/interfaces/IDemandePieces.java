package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.DemandePieces;
import java.util.List;

public interface IDemandePieces {

    public DemandePieces add(DemandePieces demandePieces);
    public void delete(DemandePieces demandePieces);
    public void deleteById(Long id);
    public DemandePieces SetAsDone(DemandePieces demandePieces);
    public List<DemandePieces> findAll();
    public DemandePieces findById(Long id);
    public DemandePieces findByInterventionId(Long id);
    public DemandePieces update(DemandePieces demandePieces);
}
