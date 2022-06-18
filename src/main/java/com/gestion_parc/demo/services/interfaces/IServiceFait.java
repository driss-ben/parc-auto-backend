package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.beans.ServiceFait;

import java.util.List;

public interface IServiceFait {

     ServiceFait create(ServiceFait serviceFait);
     ServiceFait findById(Long id);
     void delete(ServiceFait serviceFait);
     void deleteById(Long id);
     ServiceFait update(ServiceFait serviceFait);
     List<ServiceFait> findAll();

}
