package com.cesarlead.products.model.exceptions;

import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {
        log.warn("Validation errors {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("Title", "Validation errors");

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Map<String, String>> handleUnexpectedTypeException(
            UnexpectedTypeException ex) {
        log.warn("UnexpectedTypeException {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("Title", "UnexpectedTypeException");
        errors.put("message", ex.getMessage());
        errors.put("type", ex.getClass().getSimpleName());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ProductNotFoundException ex) {
        log.warn("Product not found {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("type", ex.getClass().getSimpleName());
        errors.put("Title", "Product not found");

        return ResponseEntity.status(404).body(errors);
    }

    @ExceptionHandler(ProductAlredyExistException.class)
    public ResponseEntity<Map<String, String>> handleAlredyExist(ProductAlredyExistException ex) {
        log.warn("Product alredy exist {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("type", ex.getClass().getSimpleName());
        errors.put("Title", "Product alredy exist");

        return ResponseEntity.status(409).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.warn("DataIntegrityViolationException {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("type", ex.getClass().getSimpleName());
        errors.put("Title", "DataIntegrityViolationException");

        return ResponseEntity.status(409).body(errors);
    }
}
