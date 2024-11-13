package com.fuctura.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ObjectNotFoundExeption.class)
    public ResponseEntity<StandardError> objectNotFoundExeption(ObjectNotFoundExeption err, HttpServletRequest request){
        StandardError se = new StandardError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                err.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException err, HttpServletRequest request){
        StandardError se = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                err.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException err, HttpServletRequest request){
        StandardError se = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                err.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError erros = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos", request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()){
            erros.addErros(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

}
