package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Insecte;

@Repository
public interface InsecteRepository extends JpaRepository<Insecte, Long> {

    // Méthode pour trouver le premier insecte par son nom
    Optional<Insecte> findFirstByNom(String nom);
    
    
    @Query(value = "SELECT * FROM insecte i WHERE i.poids BETWEEN :pMin AND :pMax", nativeQuery = true)
    List<Insecte> findItemBetweenPoidsMinAndMax(@Param("pMin") float pMin, @Param("pMax") float pMax);

}
