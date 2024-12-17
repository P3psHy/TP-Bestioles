package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Espece;
import com.example.demo.repository.EspeceRepository;

@Service
public class EspeceService {

    @Autowired
    private EspeceRepository especeRepository;

    public List<Espece> getItemByNameOrderByName(String valeur) {
        return especeRepository.findItemByNameOrderByName("%"+valeur+"%");
    }

    
    //REST
    public Optional<Espece> findByidEspece(long id){
        return especeRepository.findById(id);
    }

}
