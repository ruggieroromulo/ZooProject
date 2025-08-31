package com.rubix.dti.ZooProject.repository;

import com.rubix.dti.ZooProject.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}

