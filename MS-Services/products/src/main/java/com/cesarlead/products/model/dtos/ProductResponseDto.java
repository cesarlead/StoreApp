package com.cesarlead.products.model.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponseDto(


        String id,

        String name,

        String description,

        BigDecimal price,

        Integer stock,

        String category
) {
}
