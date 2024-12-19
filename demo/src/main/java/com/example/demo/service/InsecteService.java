package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InsecteDTO;
import com.example.demo.handler.GlobalExceptionHandler.EntityToCreateHasAnIdException;
import com.example.demo.mapper.InsecteMapper;
import com.example.demo.model.Insecte;
import com.example.demo.repository.InsecteRepository;

import jakarta.persistence.EntityNotFoundException;

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
    public List<InsecteDTO> findAllInsectes() {
        List<Insecte> insectes = insecteRepository.findAll();
        if (insectes.isEmpty()) {
            throw new EntityNotFoundException("Aucun insecte trouvé");
        }
        List<InsecteDTO> insectesDTO = new ArrayList<>();
        for (Insecte insecte : insectes) {
            insectesDTO.add(InsecteMapper.toDTO(insecte));
        }
        return insectesDTO;
    }

    public Insecte findByidInsecte(long id) {
        return insecteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Insecte avec l'ID " + id + " introuvable"));
    }

    public void createInsecte(Insecte insecte) {
        if (insecte.getId() != 0L) {
            throw new EntityToCreateHasAnIdException("L'entité à créer ne doit pas avoir d'ID");
        }
        try {
            insecteRepository.save(insecte);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'insecte", e);
        }
    }

    public void updateInsecte(Insecte insecte) {
        if (insecte.getId() == 0L) {
            throw new EntityNotFoundException("L'entité à mettre à jour doit avoir un ID");
        }
        if (!insecteRepository.existsById(insecte.getId())) {
            throw new EntityNotFoundException("Insecte avec l'ID " + insecte.getId() + " introuvable pour la mise à jour");
        }
        try {
            insecteRepository.save(insecte);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'insecte", e);
        }
    }

    public void deleteInsecte(long id) {
        if (!insecteRepository.existsById(id)) {
            throw new EntityNotFoundException("Insecte avec l'ID " + id + " introuvable pour suppression");
        }
        try {
            insecteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de l'insecte", e);
        }
    }
}
