package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.PieceUtilise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceUtiliseRepos extends JpaRepository<PieceUtilise,Long> {

    List<PieceUtilise> findAllByMaintenanceInterneId(Long id);
}
