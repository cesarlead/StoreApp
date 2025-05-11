package com.cesarlead.products.repository;

import com.cesarlead.products.model.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, UUID>,
        JpaSpecificationExecutor<ProductEntity> {

    Boolean existsByIdAndStock(UUID id, Integer stock);

}
