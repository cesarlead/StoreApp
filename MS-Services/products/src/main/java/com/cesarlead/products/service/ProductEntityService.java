package com.cesarlead.products.service;

import com.cesarlead.products.model.dtos.ProductFilterDto;
import com.cesarlead.products.model.dtos.ProductRequesUpdateDto;
import com.cesarlead.products.model.dtos.ProductRequestDto;
import com.cesarlead.products.model.dtos.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductEntityService {

    List<ProductResponseDto> search(ProductFilterDto filter);

    List<ProductResponseDto> findAll();

    ProductResponseDto findById(UUID id);

    ProductResponseDto save(ProductRequestDto product);

    ProductResponseDto update(UUID id, ProductRequesUpdateDto product);

    Boolean existsByQuantity(UUID id, Integer quantity);

    void delete(UUID id);
}
