package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.ServiceFait;
import com.gestion_parc.demo.repositories.ServiceFaitRepos;
import com.gestion_parc.demo.services.interfaces.IServiceFait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFaitService implements IServiceFait {

    @Autowired
    ServiceFaitRepos serviceFaitRepos;

    @Override
    public ServiceFait create(ServiceFait serviceFait) {
        serviceFaitRepos.save(serviceFait);
        return serviceFait;
    }

    @Override
    public ServiceFait findById(Long id) {
        return serviceFaitRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public void delete(ServiceFait serviceFait) {
        serviceFaitRepos.delete(serviceFait);
    }

    @Override
    public void deleteById(Long id) {
        serviceFaitRepos.deleteById(id);
    }

    @Override
    public ServiceFait update(ServiceFait serviceFait) {
        serviceFaitRepos.save(serviceFait);
        return serviceFait;
    }

    @Override
    public List<ServiceFait> findAll() {
        return serviceFaitRepos.findAll();
    }
}
