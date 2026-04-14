package com.invex.api.employees.infrastructure.controller;

import com.invex.api.employees.application.exception.NotFountException;
import com.invex.api.employees.infrastructure.response.EmployeeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvance {

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<EmployeeError> badRequestMethod(NotFountException e){
        return new ResponseEntity<>(
                new EmployeeError(HttpStatus.BAD_REQUEST.value(), e.getMessage())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeError> badResquestMethodValidator (MethodArgumentNotValidException e){
        return new ResponseEntity<>(
                new EmployeeError(HttpStatus.BAD_REQUEST.value(), e.getMessage())
                , HttpStatus.BAD_REQUEST);
    }

}
