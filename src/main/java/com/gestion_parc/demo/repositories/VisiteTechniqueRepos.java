package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.VisiteTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteTechniqueRepos extends JpaRepository<VisiteTechnique,Long> {
}
