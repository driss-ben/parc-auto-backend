package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.VisiteTechnique;
import com.gestion_parc.demo.repositories.VisiteTechniqueRepos;
import com.gestion_parc.demo.services.interfaces.IVisiteTechnique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisiteTechniqueService implements IVisiteTechnique {

    @Autowired
    VisiteTechniqueRepos visiteTechniqueRepos;

    @Override
    public void add(VisiteTechnique visiteTechnique){
        visiteTechniqueRepos.save(visiteTechnique);
    }

    @Override
    public Object findById(Long id) {
        VisiteTechnique visiteTechnique = visiteTechniqueRepos.findById(id)
                .stream().findFirst().orElse(null);
        if (visiteTechnique != null)
            return visiteTechnique;
        else throw new IllegalStateException("aucune visite technique avac id = "+ id+" n'est trouvee, peut etre vous l'avez supprimee");
    }

    @Override
    public void update(VisiteTechnique visiteTechnique) {
        visiteTechniqueRepos.save(visiteTechnique);
    }

    @Override
    public List<VisiteTechnique> findAll() {
        return visiteTechniqueRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }


    public void deleteById(Long id) {
        visiteTechniqueRepos.deleteById(id);
    }
}
