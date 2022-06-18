package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagementRepos extends JpaRepository<Engagement,Long> {

}
