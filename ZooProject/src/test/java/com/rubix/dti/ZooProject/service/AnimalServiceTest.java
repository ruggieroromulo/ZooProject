package com.rubix.dti.ZooProject.service;

import com.rubix.dti.ZooProject.model.Animal;
import com.rubix.dti.ZooProject.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceTest {

    @Test
    void testCreateOrUpdateAnimal() {
        AnimalRepository fakeRepository = Mockito.mock(AnimalRepository.class);
        AnimalService animalService = new AnimalService(fakeRepository);


        Animal animal = new Animal();
        animal.setSpecies("Dog");
        animal.setName("Rex");
        animal.setDateOfBirth(new Date());
        animal.setWeight(222225.5);

        animalService.createOrUpdateAnimal(animal);

        Mockito.verify(fakeRepository).save(animal);
    }

    @Test
    void testListAnimals() {
        AnimalRepository fakeRepository = Mockito.mock(AnimalRepository.class);
        AnimalService animalService = new AnimalService(fakeRepository);

        Animal animal1 = new Animal();
        animal1.setName("Rex");
        animal1.setSpecies("Dog");

        Animal animal2 = new Animal();
        animal2.setName("Mimi");
        animal2.setSpecies("Cat");

        List<Animal> mockedAnimals = Arrays.asList(animal1, animal2);

        Mockito.when(fakeRepository.findAll()).thenReturn(mockedAnimals);

        List<Animal> resultado = animalService.listAnimals();

        assertEquals(2, resultado.size());
        assertEquals("Rex", resultado.get(0).getName());
        assertEquals("Mimi", resultado.get(1).getName());
    }

    @Test
    void testRemoveAnimal() {
        AnimalRepository fakeRepository = Mockito.mock(AnimalRepository.class);
        AnimalService animalService = new AnimalService(fakeRepository);

        Long idParaRemover = 1L;
        animalService.removeAnimal(idParaRemover);

        Mockito.verify(fakeRepository).deleteById(idParaRemover);

    }
}