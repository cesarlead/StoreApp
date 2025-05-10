package com.cesarlead.users.model.exception;

public final class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object id) {
        super("%s con id %s no encontrado".formatted(message, id));
    }
}
