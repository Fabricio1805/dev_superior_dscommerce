package com.devsuperior.dscommerce.application.controller.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscommerce.application.dto.CustomErrorDTO;
import com.devsuperior.dscommerce.application.dto.FieldMessagedTO;
import com.devsuperior.dscommerce.application.dto.ValidationErrorDTO;
import com.devsuperior.dscommerce.infrastructure.exception.DatabaseException;
import com.devsuperior.dscommerce.infrastructure.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorDTO> NotFound(NotFoundException notFoundException, HttpServletRequest request) {
        CustomErrorDTO customError = new CustomErrorDTO(Instant.now(), HttpStatus.NOT_FOUND.value(),
                notFoundException.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> Database(DatabaseException databaseException, HttpServletRequest request) {
        CustomErrorDTO customError = new CustomErrorDTO(Instant.now(), HttpStatus.CONFLICT.value(),
                databaseException.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> MethodArgumentNotValidation(MethodArgumentNotValidException error, HttpServletRequest request) {
        ValidationErrorDTO customError = new ValidationErrorDTO(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                null, request.getRequestURI());

        for (FieldError err : error.getBindingResult().getFieldErrors()) {
            customError.addError(new FieldMessagedTO(err.getField(), err.getDefaultMessage()));
        }        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customError);
    }


}
