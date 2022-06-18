package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.TypeHuile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TypeHuileRepos extends JpaRepository<TypeHuile,Long> {

    public TypeHuile findByNom(String nom);
    public void deleteByNom(String nom);

}
