package com.example.demo.model;


import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Insecte {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private float poids;

    @ManyToMany
    @JoinTable(
        name = "insecte_categorie",
        joinColumns = @JoinColumn(name = "insecte_id"),
        inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<Categorie> categories;

    @ManyToOne
    @JoinColumn(name = "espece_id", nullable = false)
    private Espece espece;


    
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

    public float getPoids() {
        return this.poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public Set<Categorie> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Categorie> categories) {
        this.categories = categories;
    }


    public Espece getEspece() {
        return this.espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    };

}
