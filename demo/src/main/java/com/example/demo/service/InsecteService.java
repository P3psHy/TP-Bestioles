package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Insecte;
import com.example.demo.repository.InsecteRepository;

@Service
public class InsecteService {

    @Autowired
    private InsecteRepository insecteRepository;

    // Méthode pour trouver un insecte par son nom
    public Insecte findInsecteFromNom(String nom) {
        return insecteRepository.findFirstByNom(nom).orElse(null);
    }

    public List<Insecte> getItemBetweenPoidsMinAndMax(float pMin, float pMax){
        return insecteRepository.findItemBetweenPoidsMinAndMax(pMin,pMax);
    }


    //REST
    public List<Insecte> findAllInsectes() {
        List<Insecte> insectes = insecteRepository.findAll();
        if (insectes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun insecte trouvé");
        }
        return insectes;
    }
    public Insecte findByidInsecte(long id) {
        return insecteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Insecte avec l'ID " + id + " introuvable"));
    }

    public void createInsecte(Insecte insecte) {
        try {
            insecteRepository.save(insecte);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la création de l'insecte", e);
        }
    }

    public void updateInsecte(Insecte insecte) {
        if (!insecteRepository.existsById(insecte.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Insecte avec l'ID " + insecte.getId() + " introuvable pour la mise à jour");
        }
        try {
            insecteRepository.save(insecte);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de l'insecte", e);
        }
    }

    public void deleteInsecte(long id) {
        if (!insecteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Insecte avec l'ID " + id + " introuvable pour suppression");
        }
        try {
            insecteRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de l'insecte", e);
        }
    }

}
