package com.cesarlead.users.model.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    // -- NotFoundException ---------------------------------------------------
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(NotFoundException ex) {
        ProblemDetail pd = createProblemDetail(HttpStatus.NOT_FOUND,
                "Recurso no encontrado", ex.getMessage(), "/errors/not-found");
        return ResponseEntity.status(pd.getStatus()).body(pd);
    }

    // -- ValidationException (manual) -----------------------------------------
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ProblemDetail> handleValidation(ValidationException ex) {
        ProblemDetail pd = createProblemDetail(HttpStatus.BAD_REQUEST,
                "Validación fallida", ex.getMessage(), "/errors/validation");
        pd.setProperty("fieldErrors", ex.getErrors());
        return ResponseEntity.status(pd.getStatus()).body(pd);
    }

    // -- Bean Validation en @RequestBody/@PathVariable ------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleBindException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (a, b) -> a  // en caso de duplicados, nos quedamos con el primero
                ));
        ProblemDetail pd = createProblemDetail(HttpStatus.BAD_REQUEST,
                "Validación de parámetros", "Hay campos inválidos", "/errors/validation");
        pd.setProperty("fieldErrors", fieldErrors);
        return ResponseEntity.status(pd.getStatus()).body(pd);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> violations = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (a, b) -> a
                ));
        ProblemDetail pd = createProblemDetail(HttpStatus.BAD_REQUEST,
                "Violación de restricciones", "Hay restricciones violadas", "/errors/validation");
        pd.setProperty("constraintViolations", violations);
        return ResponseEntity.status(pd.getStatus()).body(pd);
    }

    // -- Caída inesperada -----------------------------------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpected(Exception ex) {
        ProblemDetail pd = createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor", ex.getMessage(), "/errors/internal");
        return ResponseEntity.status(pd.getStatus()).body(pd);
    }

    // -- Factory method común (DRY) ------------------------------------------
    private ProblemDetail createProblemDetail(HttpStatus status,
                                              String title,
                                              String detail,
                                              String instancePath) {
        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle(title);
        pd.setDetail(detail);
        pd.setInstance(URI.create(instancePath));
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }
}
