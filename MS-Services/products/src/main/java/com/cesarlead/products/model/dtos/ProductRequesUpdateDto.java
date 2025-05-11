package com.cesarlead.products.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequesUpdateDto(

        @NotBlank(message = "Id is required")
        UUID id,

        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than zero")
        BigDecimal price,

        @NotNull(message = "Stock is required")
        @Positive(message = "Stock must be greater than zero")
        Integer stock,

        @NotBlank(message = "Category is required")
        @Size(min = 3, max = 100, message = "Category must be between 3 and 100 characters")
        String category
) {
}
