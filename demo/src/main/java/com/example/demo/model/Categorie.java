package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Categorie {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    @ManyToMany(mappedBy = "categories")
    private Set<Insecte> insectes;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Insecte> getInsectes() {
        return this.insectes;
    }

    public void setInsectes(Set<Insecte> insectes) {
        this.insectes = insectes;
    }

}
