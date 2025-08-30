package com.rubix.dti.ZooProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Animal{

    @Id
    @Column(unique = true)
    int  id;

    @Column
    String name;

    @Column
    Date dateOfBirth;

    @Column
    int age;

    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;





}
