package com.gestion_parc.demo.repositories;

import com.gestion_parc.demo.beans.DemandeIntervention;
import com.gestion_parc.demo.beans.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntretienRepos extends JpaRepository<Entretien,Long> {

    public List<Entretien> findAllByTypeIntervention(String type);
    public List<Entretien> findAllByTypeInterventionAndEtat(String type , Boolean etat);
    public List<Entretien> findAllByDate(LocalDate date);
    public Entretien findByDemandeIntervention(DemandeIntervention demandeIntervention);
    public Entretien findByDemandeInterventionId(Long id);

}
