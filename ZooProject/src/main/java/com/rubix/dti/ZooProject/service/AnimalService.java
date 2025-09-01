package com.rubix.dti.ZooProject.service;

import com.rubix.dti.ZooProject.model.Animal;
import com.rubix.dti.ZooProject.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Animal> listAnimals(){
        return animalRepository.findAll();
    }

    public void removeAnimal(Long id){
         animalRepository.deleteById(id);
    }

}
