package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Espece;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EspeceRepository extends JpaRepository<Espece, Long>{

    @Query(value = "SELECT * FROM espece e WHERE e.nom LIKE :nom ORDER BY e.nom", nativeQuery = true)
    List<Espece> findItemByNameOrderByName(@Param("nom") String nom);

}
