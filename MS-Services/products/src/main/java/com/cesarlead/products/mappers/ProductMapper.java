package com.cesarlead.products.mappers;

import com.cesarlead.products.model.dtos.ProductRequesUpdateDto;
import com.cesarlead.products.model.dtos.ProductRequestDto;
import com.cesarlead.products.model.dtos.ProductResponseDto;
import com.cesarlead.products.model.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductMapper {

    public ProductResponseDto fromEntity(ProductEntity entity) {

        if (entity != null) {
            return ProductResponseDto.builder()
                    .id(entity.getId().toString())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .price(entity.getPrice())
                    .stock(entity.getStock())
                    .category(entity.getCategory())
                    .build();
        }
        return null;
    }

    public ProductEntity toEntity(ProductRequestDto dto) {
        if (dto != null) {
            return ProductEntity.builder()
                    .name(dto.name())
                    .description(dto.description())
                    .price(dto.price())
                    .stock(dto.stock())
                    .category(dto.category())
                    .createdAt(new Date())
                    .build();
        }
        return null;
    }

    public ProductEntity toEntityUpdate(ProductRequesUpdateDto dto) {
        if (dto != null) {
            return ProductEntity.builder()
                    .name(dto.name())
                    .description(dto.description())
                    .price(dto.price())
                    .stock(dto.stock())
                    .category(dto.category())
                    .updatedAt(new Date())
                    .build();
        }
        return null;
    }
}
