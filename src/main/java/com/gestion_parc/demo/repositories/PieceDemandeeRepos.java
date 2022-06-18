package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.DemandePieces;
import com.gestion_parc.demo.beans.PieceDemandee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceDemandeeRepos extends JpaRepository<PieceDemandee,Long> {


}
