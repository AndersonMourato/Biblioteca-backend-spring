package com.fuctura.biblioteca.exceptions;

public class DataIntegrityViolationException extends  RuntimeException{
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
