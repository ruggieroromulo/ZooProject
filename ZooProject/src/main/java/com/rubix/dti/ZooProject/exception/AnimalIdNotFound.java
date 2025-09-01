package com.rubix.dti.ZooProject.exception;

public class AnimalIdNotFound extends RuntimeException{
    public AnimalIdNotFound() {
        super("Animal ID not found.");
    }
}
