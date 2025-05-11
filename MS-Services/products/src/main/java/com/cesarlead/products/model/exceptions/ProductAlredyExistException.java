package com.cesarlead.products.model.exceptions;

public class ProductAlredyExistException extends RuntimeException {
    public ProductAlredyExistException(String message) {
        super(message);
    }
}
