package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.ServiceFait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceFaitRepos  extends JpaRepository<ServiceFait,Long> {

}
