package com.cesarlead.users.model.exception;


public sealed class AppException extends RuntimeException
        permits NotFoundException, ValidationException {

    protected AppException(String message) {
        super(message);
    }
}

