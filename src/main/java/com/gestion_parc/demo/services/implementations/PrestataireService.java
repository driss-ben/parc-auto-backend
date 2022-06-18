package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Prestataire;
import com.gestion_parc.demo.repositories.PrestataireRepos;
import com.gestion_parc.demo.services.interfaces.IPrestataire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestataireService implements IPrestataire {

    @Autowired
    PrestataireRepos prestataireRepos;

    @Override
    public Prestataire create(Prestataire prestataire) {
        prestataireRepos.save(prestataire);
        return prestataire;
    }

    @Override
    public Prestataire findById(Long id) {
        return prestataireRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Prestataire findByNom(String nom) {
        return prestataireRepos.findByNom(nom);
    }

    @Override
    public void delete(Prestataire prestataire) {
        prestataireRepos.delete(prestataire);
    }

    @Override
    public void deleteById(Long id) {
        prestataireRepos.deleteById(id);
    }

    @Override
    public Prestataire update(Prestataire prestataire) {
        prestataireRepos.save(prestataire);
        return prestataire;
    }

    @Override
    public List<Prestataire> findAll() {
        return prestataireRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
}
