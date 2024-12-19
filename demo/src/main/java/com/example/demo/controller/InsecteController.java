package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InsecteDTO;
import com.example.demo.model.Espece;
import com.example.demo.model.Insecte;
import com.example.demo.service.EspeceService;
import com.example.demo.service.InsecteService;


@RestController
public class InsecteController {
    
    @Autowired
    private InsecteService insecteService;
    @Autowired
    private EspeceService especeService;

    @GetMapping("/insecte")
    public List<InsecteDTO> findAllInsectes(){
        return insecteService.findAllInsectes();
    }

    @GetMapping("/insecte/{id}")
    public Insecte findInsecte(@PathVariable long id){
        return insecteService.findByidInsecte(id);
    }

    @PostMapping("/insecte")
    public void createInsecte(@RequestParam String nom, @RequestParam float poids, @RequestParam int especeId){
        // Récupère l'espèce directement (en supposant qu'elle existe)
        Espece espece = especeService.findByidEspece(especeId)
        .orElseThrow(() -> new RuntimeException("Espèce introuvable avec l'ID : " + especeId));

        // Crée un nouvel insecte
        Insecte insecte = new Insecte();
            insecte.setNom(nom);
            insecte.setPoids(poids);
            insecte.setEspece(espece);

        // Enregistre l'insecte
        insecteService.createInsecte(insecte);

        
    }

    @PutMapping("/insecte/{id}")
    public void updateInsecte(@PathVariable long id, @RequestParam String nom, @RequestParam float poids, @RequestParam int especeId){
        Insecte insecte = insecteService.findByidInsecte(id);

        // Crée un nouvel insecte
        if(nom != ""){
            insecte.setNom(nom);
        }
        if(poids != 0){
            insecte.setPoids(poids);
        }
        if(especeId != 0){
            Espece espece = especeService.findByidEspece(especeId)
            .orElseThrow(() -> new RuntimeException("Espèce introuvable avec l'ID : " + especeId));
            insecte.setEspece(espece);
        }
        

        // Enregistre l'insecte
        insecteService.updateInsecte(insecte);
    }

    @DeleteMapping("/insecte/{id}")
    public void deleteInsecte(@PathVariable long id){
        insecteService.deleteInsecte(id);
    }


}
