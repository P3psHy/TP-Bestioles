package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.InsecteDTO;
import com.example.demo.model.Categorie;
import com.example.demo.model.Insecte;

public class InsecteMapper {

    public static InsecteDTO toDTO(Insecte insecte) {
        if (insecte == null) {
            return null;
        }
        
        InsecteDTO dto = new InsecteDTO();
        dto.setId(insecte.getId());
        dto.setNom(insecte.getNom());
        dto.setPoids(insecte.getPoids());
        
        // Convertir les catégories en une liste de noms
        List<String> categories = insecte.getCategories().stream()
                                         .map(Categorie::getNom)
                                         .collect(Collectors.toList());
        dto.setCategories(categories);
        
        return dto;
    }


}
