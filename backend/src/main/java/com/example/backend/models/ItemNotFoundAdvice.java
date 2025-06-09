package com.example.backend.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemNotFoundAdvice {
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    ErrorDTO itemNotFoundHandler(ItemNotFoundException exception) {
        return new ErrorDTO(exception.getMessage());
    }
}
