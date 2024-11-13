package com.fuctura.biblioteca.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private List<FieldMensage> erros = new ArrayList<>();

    public ValidationError(LocalDateTime timestemp, Integer status, String menssage, String path) {
        super(timestemp, status, menssage, path);
    }

    public ValidationError() {
    }

    public List<FieldMensage> getErros() {
        return erros;
    }

    public void setErros(List<FieldMensage> erros) {
        this.erros = erros;
    }

    public void addErros(String fieldName, String mensage){
        this.erros.add(new FieldMensage(fieldName, mensage));
    }
}
