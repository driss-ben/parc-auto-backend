package com.gestion_parc.demo.repositories;


import com.gestion_parc.demo.beans.DemandePieces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandePieceRepos extends JpaRepository<DemandePieces,Long> {

    public DemandePieces findByMaintenanceInterneId(Long id);

}
