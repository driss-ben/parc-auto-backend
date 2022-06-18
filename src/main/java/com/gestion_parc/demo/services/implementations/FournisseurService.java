package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Fournisseur;
import com.gestion_parc.demo.repositories.FournisseurRepos;
import com.gestion_parc.demo.services.interfaces.IFournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService implements IFournisseur {

    @Autowired
    FournisseurRepos fournisseurRepos;

    @Override
    public Fournisseur add(Fournisseur fournisseur) {

        Fournisseur fournisseur2 = fournisseurRepos.findByNom(fournisseur.getNom());
        Fournisseur fournisseur1 = fournisseurRepos.findByEmail(fournisseur.getEmail());

        if (fournisseur1!=null) {
            throw new IllegalStateException("fournisseur deja existe avec email : " + fournisseur.getEmail());
        }else if (fournisseur2 !=null){
            throw new IllegalStateException("fournisseur deja existe avec le nom : " + fournisseur.getNom());
        } else{
            fournisseurRepos.save(fournisseur);
        }

        return fournisseur;
    }

    @Override
    public Fournisseur findById(Long id) {
        return fournisseurRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Fournisseur findByNom(String nom) {
        return fournisseurRepos.findByNom(nom);
    }

    @Override
    public void delete(Fournisseur fournisseur) {
        Optional<Fournisseur> fournisseurOptional=fournisseurRepos.findById(fournisseur.getId());
        if (fournisseurOptional.isPresent()) fournisseurRepos.delete(fournisseur);
        else{
            // Doesnt exist exception
        }

    }

    @Override
    public void deleteById(Long id) {
        Optional<Fournisseur> fournisseurOptional = fournisseurRepos.findById(id);
        if (fournisseurOptional.isPresent())
            fournisseurRepos.deleteById(id);
        else{
            throw new IllegalStateException(String.format("aucun fournisseur avec id = {} n'set trouvee", id));
        }
    }

    @Override
    public Fournisseur update(Fournisseur fournisseur) {
        Fournisseur fournisseurOptional = fournisseurRepos.
                findById(fournisseur.getId()).stream().findFirst().orElse(null);

        if(fournisseurOptional != null ) fournisseurRepos.save(fournisseur);

        return fournisseur;
    }

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
}
