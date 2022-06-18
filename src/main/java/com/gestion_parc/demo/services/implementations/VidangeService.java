package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Huile;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.beans.Vidange;
import com.gestion_parc.demo.repositories.HuileRepose;
import com.gestion_parc.demo.repositories.VehiculeRepos;
import com.gestion_parc.demo.repositories.VidangeRepos;
import com.gestion_parc.demo.services.interfaces.IVidange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class VidangeService implements IVidange {

    @Autowired
    VidangeRepos vidangeRepos;

    @Autowired
    HuileRepose huileRepose;

    @Autowired
    VehiculeRepos vehiculeRepos;

    @Override
    public Vidange add(Vidange vidange) {
        if (vidange.getHuile().getId()==null || vidange.getVehicule().getId()==null){
            throw new IllegalStateException("huile ou vehicule n'est pas selectionner");
        }else {

            Huile huile = huileRepose.findById(vidange.getHuile().getId())
                    .stream().findFirst().orElse(null);
            Vehicule vehicule = vehiculeRepos.findById(vidange.getVehicule().getId())
                    .stream().findFirst().orElse(null);


            if (huile.getQuantiteStock() - vidange.getQuantiteHuile() >= 0) {
                huile.setQuantiteStock(
                        huile.getQuantiteStock() - vidange.getQuantiteHuile()
                );
                huileRepose.save(huile);
                vidange.setKilometrage(vehicule.getKilometrage());
                vidange.setCout(huile.getCout() * vidange.getQuantiteHuile());
                vidangeRepos.save(vidange);
                return vidange;
            } else {
                // Quantity not enough exception
                throw new IllegalStateException("quantite du huile dans le stock insuffisante.");
            }
        }
    }

    @Override
    public void delete(Vidange vidange) {
       try {
           vidangeRepos.delete(vidange);
       }
       catch (Exception e){
           throw new IllegalStateException(e.getMessage());
       }
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            Vidange vidange=this.findById(id);
            Huile huile=vidange.getHuile();
            huile.setQuantiteStock(huile.getQuantiteStock() + vidange.getQuantiteHuile());
            vidangeRepos.deleteById(id);
            huileRepose.save(huile);
            return true;
        }
        catch (Exception e){
            throw new IllegalStateException("Impossible de supprimer l'operation de vidange");
        }
    }

    @Override
    public Vidange findById(Long id) {
        return vidangeRepos.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public Vidange update(Vidange vidange) {
        Vidange vid=this.findById(vidange.getId());

        if(vid != null){
            if(vid.getHuile()==vidange.getHuile()){
                Huile huile=vidange.getHuile();
                Long theRest= huile.getQuantiteStock()
                        -
                        (vidange.getQuantiteHuile()-vid.getQuantiteHuile()) ;

                if(theRest >=0){
                    huile.setQuantiteStock(theRest);
                    vidange.setCout(vidange.getHuile().getCout()*vidange.getQuantiteHuile());
                    vidangeRepos.save(vidange);
                }
                else{
                    // Quantity not enough exception
                    return null;
                }
            }
            else{

                Huile oldHuile=vid.getHuile();
                Huile newHuile=huileRepose.findById(vidange.getHuile().getId()).stream().findFirst().orElse(null);
                Long theRest=newHuile.getQuantiteStock()-vidange.getQuantiteHuile();
                if(theRest>=0){
                    oldHuile.setQuantiteStock(oldHuile.getQuantiteStock()+vid.getQuantiteHuile());
                    huileRepose.save(oldHuile);
                    newHuile.setQuantiteStock(newHuile.getQuantiteStock()-vidange.getQuantiteHuile());
                    huileRepose.save(newHuile);
                    vidange.setCout(newHuile.getCout()*vidange.getQuantiteHuile());
                    vidangeRepos.save(vidange);
                }
                else {
                    // Quantity not enough exception
//                    throw new IllegalStateException("Quantite Insuffisante");
                    return null;
                }
            }
            return vidange;
        }
        else {
            // Doesnt exist exception
//            throw new IllegalStateException("Vidange avec id =" + vid.getId() + " n'exist pas");
            return null;
        }

    }

    @Override
    public List<Vidange> findAll() {
        return vidangeRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Vidange> findAllByHuile(Long id) {
        return vidangeRepos.findAllByHuileId(id);
    }

    @Override
    public List<Vidange> findAllByVehicule(Long id) {
        return vidangeRepos.findAllByVehiculeId(id);
    }

    @Override
    public List<Vidange> findAllByDate(LocalDate date) {
        return vidangeRepos.findAllByDate(date);
    }

    @Override
    public Vidange findLastByVehicule(Vehicule vehicule) {
        return vidangeRepos.findLastByVehicule(vehicule);
    }

    @Override
    public List<Vidange> findAllByVehiculeAndYear(Long id , LocalDate localDate) {
        return vidangeRepos.findAllByVehiculeIdAndDateAfter(id,localDate);
    }

}
