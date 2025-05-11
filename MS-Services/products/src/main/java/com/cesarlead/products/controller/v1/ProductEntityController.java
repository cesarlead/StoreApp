package com.cesarlead.products.controller.v1;


import com.cesarlead.products.model.dtos.ProductFilterDto;
import com.cesarlead.products.model.dtos.ProductRequesUpdateDto;
import com.cesarlead.products.model.dtos.ProductRequestDto;
import com.cesarlead.products.model.dtos.ProductResponseDto;
import com.cesarlead.products.service.ProductEntityService;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductEntityController {

    private final ProductEntityService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        log.info("Requesting all products");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> search(
            @QueryParam("name") String name,
            @QueryParam("category") String category,
            @QueryParam("stock") Integer stock,
            @QueryParam("price") BigDecimal price
    ) {
        log.info("Requesting all products");

        ProductFilterDto filter = new ProductFilterDto(
                name,
                category,
                stock,
                price
        );

        return ResponseEntity.ok(service.search(filter));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponseDto> findById(
            @PathVariable("id") UUID id
    ) {
        log.info("Requesting product with id {}", id);

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByQuantity(
            @QueryParam("id") UUID id,
            @QueryParam("quantity") Integer quantity
    ) {
        log.info("Requesting existsByQuantity {} {}", id, quantity);
        return ResponseEntity.ok(service.existsByQuantity(id, quantity));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(
            @Valid
            @RequestBody ProductRequestDto product
    ) {
        log.info("Saving product {}", product);
        return ResponseEntity.ok(service.save(product));
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(
            @Valid
            @RequestBody ProductRequesUpdateDto product
    ) {
        log.info("Updating product {}", product);
        return ResponseEntity.ok(service.update(product.id(), product));
    }

}