package com.epam.esm.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(int id) {
        super("Person with id " + id + " does not exist");
    }
}
