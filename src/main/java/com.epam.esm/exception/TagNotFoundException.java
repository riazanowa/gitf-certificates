package com.epam.esm.exception;

public class TagNotFoundException extends RuntimeException{

    public TagNotFoundException(int id) {
        super("Tag with id " + id + " does not exist");
    }

}
