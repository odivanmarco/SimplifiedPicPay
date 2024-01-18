package com.simplifiedpicpay.infra;

import com.simplifiedpicpay.dtos.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDto dto = new ExceptionDto("Documento e/ou email já cadastrado!", "400");
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatEntityNotFound(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralExeption(Exception exception){
        ExceptionDto dto = new ExceptionDto(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(dto);
    }
}