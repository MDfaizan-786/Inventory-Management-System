
package com.blinkit.products.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.blinkit.products.dto.ProductsRequest;
import com.blinkit.products.dto.ProductsResponse;
import com.blinkit.products.enums.ProductsEnum;
import com.blinkit.products.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<ProductsResponse> createProduct(
            @Valid @RequestBody ProductsRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productsService.saveProduct(request));
    }

    // READ ALL
    @GetMapping("/readAll")
    public List<ProductsResponse> getAllProducts() {
        return productsService.getAllProducts();
    }

    // READ BY ID
    @GetMapping("/readById/{id}")
    public ProductsResponse getProductById(@PathVariable Long id) {
        return productsService.getProductById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ProductsResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductsRequest request) {

        return productsService.updateProduct(id, request);
    }

    // DELETE
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        productsService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    // DERIVED METHOD 1: FIND BY EXACT NAME
    @GetMapping("/search/by-name")
    public List<ProductsResponse> getProductsByName(
            @RequestParam String name) {

        return productsService.getProductsByName(name);
    }

    

    // DERIVED METHOD 2: FIND BY PRICE RANGE
    @GetMapping("/filter/price")
    public List<ProductsResponse> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {

        return productsService.getProductsByPriceRange(
                minPrice, maxPrice);
    }

    // DERIVED METHOD 3: FIND BY STATUS
    @GetMapping("/filter/status")
    public List<ProductsResponse> getProductsByStatus(
            @RequestParam ProductsEnum status) {

        return productsService.getProductsByStatus(status);
    }

   
}
