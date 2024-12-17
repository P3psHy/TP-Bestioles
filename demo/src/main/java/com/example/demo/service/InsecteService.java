package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return insecteRepository.findAll();
    }
    public Optional<Insecte> findByidInsecte(long id){
        return insecteRepository.findById(id);
    }

    public void createInsecte(Insecte insecte) {
        insecteRepository.save(insecte);
    }

    public void updateInsecte(Insecte insecte) {
        insecteRepository.save(insecte);
    }

    public void deleteInsecte(long id) {
        insecteRepository.deleteById(id);;
    }

}
