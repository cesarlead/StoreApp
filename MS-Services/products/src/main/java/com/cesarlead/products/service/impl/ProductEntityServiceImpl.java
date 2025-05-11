package com.cesarlead.products.service.impl;

import com.cesarlead.products.mappers.ProductMapper;
import com.cesarlead.products.model.dtos.ProductFilterDto;
import com.cesarlead.products.model.dtos.ProductRequesUpdateDto;
import com.cesarlead.products.model.dtos.ProductRequestDto;
import com.cesarlead.products.model.dtos.ProductResponseDto;
import com.cesarlead.products.model.entities.ProductEntity;
import com.cesarlead.products.model.exceptions.ProductNotFoundException;
import com.cesarlead.products.repository.ProductEntityRepository;
import com.cesarlead.products.service.ProductEntityService;
import com.cesarlead.products.spec.ProductSpecs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductEntityServiceImpl implements ProductEntityService {

    private final ProductEntityRepository repository;
    private final ProductMapper mapper;


    @Override
    public List<ProductResponseDto> search(ProductFilterDto filter) {
        log.info("Processing search");

        Specification<ProductEntity> spec = Specification.where(null);

        if (filter.name() != null) {
            spec = ProductSpecs.nameContains(lowerCase(filter.name()));
        }

        if (filter.category() != null) {
            spec = ProductSpecs.categoryContains(lowerCase(filter.category()));
        }

        if (filter.stock() != null) {
            spec = ProductSpecs.stockGreaterThan(filter.stock());
        }

        if (filter.price() != null) {
            spec = ProductSpecs.priceGreaterThan(filter.price());
        }

        return repository.findAll(spec).stream().map(mapper::fromEntity).toList();
    }

    @Override
    public List<ProductResponseDto> findAll() {
        log.info("Processing findAll");
        return repository.findAll().stream().map(mapper::fromEntity).toList();
    }

    @Override
    public ProductResponseDto findById(UUID id) {
        log.info("Processing findById {}", id);
        return repository.findById(id).map(mapper::fromEntity)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    @Override
    public ProductResponseDto save(ProductRequestDto product) {
        log.info("Processing save {}", product);
        return mapper.fromEntity(repository.save(mapper.toEntity(product)));
    }

    @Override
    public ProductResponseDto update(UUID id, ProductRequesUpdateDto product) {
        log.info("Processing update {}", product);
        return mapper.fromEntity(repository.save(mapper.toEntityUpdate(product)));
    }

    @Override
    public Boolean existsByQuantity(UUID id, Integer quantity) {
        log.info("Processing existsByQuantity {}", quantity);
        return repository.existsByIdAndStock(id, quantity);
    }

    @Override
    public void delete(UUID id) {
        log.info("Processing delete {}", id);
        repository.deleteById(id);
    }

    private String lowerCase(String str) {
        return str.toLowerCase();
    }
}
