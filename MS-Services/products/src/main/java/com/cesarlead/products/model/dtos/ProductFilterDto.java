package com.cesarlead.products.model.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductFilterDto(
        String name,
        String category,
        Integer stock,
        BigDecimal price
) {
}
