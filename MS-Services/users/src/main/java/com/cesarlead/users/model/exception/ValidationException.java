package com.cesarlead.users.model.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public final class ValidationException extends AppException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Validation error");
        this.errors = errors;
    }

}
