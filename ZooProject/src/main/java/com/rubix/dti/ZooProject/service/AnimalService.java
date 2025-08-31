package com.rubix.dti.ZooProject.service;

import com.rubix.dti.ZooProject.model.Animal;
import com.rubix.dti.ZooProject.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }



    public Animal createAnimal(Animal animal){
        return animalRepository.save(animal);
    }


}
