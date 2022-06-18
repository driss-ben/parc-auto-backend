package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Engagement;
import com.gestion_parc.demo.repositories.EngagementRepos;
import com.gestion_parc.demo.services.interfaces.IEngagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngagementService implements IEngagement {

    @Autowired
    EngagementRepos engagementRepos;

    @Override
    public Engagement add(Engagement engagement) {
        engagementRepos.save(engagement);
        return engagement;
    }

    @Override
    public Engagement findById(Long id) {
        return engagementRepos.findById(id).stream().findFirst().orElse(null);
    }


    @Override
    public void delete(Engagement engagement) {
        engagementRepos.delete(engagement);
    }

    @Override
    public void deleteById(Long id) {
        engagementRepos.deleteById(id);
    }

    @Override
    public Engagement update(Engagement engagement) {
        engagementRepos.save(engagement);
        return engagement;
    }

    @Override
    public List<Engagement> findAll() {
        return engagementRepos.findAll();
    }

}
