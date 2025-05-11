package com.cesarlead.products.spec;

import com.cesarlead.products.model.entities.ProductEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public final class ProductSpecs {

    public static Specification<ProductEntity> nameContains(String name) {
        String pattern = "%" + name.toLowerCase() + "%";
        return (root, query, cb) ->
                // lower(name) LIKE '%pattern%'
                cb.like(cb.lower(root.get("name")), pattern);
    }

    public static Specification<ProductEntity> categoryContains(String category) {
        String pattern = "%" + category.toLowerCase() + "%";
        return (root, query, cb) ->
                // lower(category) LIKE '%pattern%'
                cb.like(cb.lower(root.get("category")), pattern);
    }

    public static Specification<ProductEntity> stockGreaterThan(Integer stock) {
        return (root, query, cb) ->
                cb.greaterThan(root.get("stock"), stock);
    }

    public static Specification<ProductEntity> priceGreaterThan(BigDecimal price) {
        return (root, query, cb) ->
                cb.greaterThan(root.get("price"), price);
    }
}
