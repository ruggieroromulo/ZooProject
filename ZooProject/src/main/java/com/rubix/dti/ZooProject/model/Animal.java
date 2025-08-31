package com.rubix.dti.ZooProject.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Animal{

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long  id;

    @Column
    String species;

    @Column
    String name;

    @Column
    Date dateOfBirth;

    @Column
    Double weight;

    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;


    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;

    public Animal(){
    }

    public Animal(String name, Date dateOfBirth, Double weight,  String species) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.species = species;
        this.createdAt = LocalDateTime.now(ZoneId.of("UTC-3"));
        this.updatedAt = LocalDateTime.now(ZoneId.of("UTC-3"));
    }



    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
