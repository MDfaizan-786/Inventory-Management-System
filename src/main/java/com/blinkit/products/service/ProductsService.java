
package com.blinkit.products.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.blinkit.category.entity.CategoryEntity;
import com.blinkit.category.repository.CategoryRepository;
import com.blinkit.products.dto.ProductsRequest;
import com.blinkit.products.dto.ProductsResponse;
import com.blinkit.products.entity.ProductsEntity;
import com.blinkit.products.enums.ProductsEnum;
import com.blinkit.products.repository.ProductsRepository;

@Service
public class ProductsService {

	private final ProductsRepository productsRepository;
	private final CategoryRepository categoryRepository;

	private final AtomicInteger counter = new AtomicInteger(1);

	public ProductsService(ProductsRepository productsRepository, CategoryRepository categoryRepository) {

		this.productsRepository = productsRepository;
		this.categoryRepository = categoryRepository;
	}

	// CREATE
	public ProductsResponse saveProduct(ProductsRequest request) {

		CategoryEntity category = findCategoryOrThrow(request.getCategoryId());

		ProductsEntity entity = new ProductsEntity();

		entity.setName(request.getName());
		entity.setProductCode(generateProductCode());
		entity.setDescription(request.getDescription());
		entity.setPrice(request.getPrice());
		entity.setStockQuantity(request.getStockQuantity());
		entity.setStatus(request.getStatus());
		entity.setCategory(category);

		ProductsEntity savedEntity = productsRepository.save(entity);

		return toResponse(savedEntity);
	}

	// READ ALL
	public List<ProductsResponse> getAllProducts() {

		List<ProductsEntity> products = productsRepository.findAll();

		return toResponseList(products);
	}

	// READ BY ID
	public ProductsResponse getProductById(Long id) {

		ProductsEntity entity = findProductOrThrow(id);

		return toResponse(entity);
	}

	// UPDATE
	public ProductsResponse updateProduct(Long id, ProductsRequest request) {

		ProductsEntity entity = findProductOrThrow(id);

		CategoryEntity category = findCategoryOrThrow(request.getCategoryId());

		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity.setPrice(request.getPrice());
		entity.setStockQuantity(request.getStockQuantity());
		entity.setStatus(request.getStatus());
		entity.setCategory(category);
		entity.setDimensions(request.getDimensions());

		// Keep the existing product code during an update.
		ProductsEntity updatedEntity = productsRepository.save(entity);

		return toResponse(updatedEntity);
	}

	// DELETE
	public void deleteProduct(Long id) {

		ProductsEntity entity = findProductOrThrow(id);

		productsRepository.delete(entity);
	}

	// 1. DERIVED METHOD: FIND BY EXACT NAME
	public List<ProductsResponse> getProductsByName(String name) {

		return toResponseList(productsRepository.findByName(name));
	}

	// 2. DERIVED METHOD: FIND BY PRICE RANGE
	public List<ProductsResponse> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {

		if (minPrice == null || maxPrice == null || minPrice.compareTo(maxPrice) > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid price range");
		}

		return toResponseList(productsRepository.findByPriceBetween(minPrice, maxPrice));
	}

	// 3. DERIVED METHOD: FIND BY STATUS
	public List<ProductsResponse> getProductsByStatus(ProductsEnum status) {

		return toResponseList(productsRepository.findByStatus(status));
	}


	// 4. DERIVED METHOD: SORT BY PRICE ASCENDING
	public List<ProductsResponse> getProductsSortedByPriceAsc() {

		return toResponseList(productsRepository.findAllByOrderByPriceAsc());
	}
	
	// QUERY: FIND PRODUCTS WITH PRICE GREATER THAN 1000
	public List<ProductsResponse> findExpensiveProducts() {

	    List<ProductsEntity> products = productsRepository.findExpensiveProducts();

	    return toResponseList(products);
	}

	// FIND PRODUCT OR THROW 404
	private ProductsEntity findProductOrThrow(Long id) {

		return productsRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
	}

	// FIND CATEGORY OR THROW 404
	private CategoryEntity findCategoryOrThrow(Long id) {

		return categoryRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + id));
	}

	// CONVERT ENTITY LIST TO RESPONSE DTO LIST
	private List<ProductsResponse> toResponseList(List<ProductsEntity> products) {

		List<ProductsResponse> responses = new ArrayList<>();

		for (ProductsEntity product : products) {
			responses.add(toResponse(product));
		}

		return responses;
	}

	// ENTITY TO RESPONSE DTO
	private ProductsResponse toResponse(ProductsEntity entity) {

		ProductsResponse response = new ProductsResponse();

		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setProductCode(entity.getProductCode());
		response.setDescription(entity.getDescription());
		response.setPrice(entity.getPrice());
		response.setStockQuantity(entity.getStockQuantity());
		response.setStatus(entity.getStatus());

		if (entity.getCategory() != null) {
			response.setCategoryId(entity.getCategory().getId());
			response.setCategoryName(entity.getCategory().getName());
		}

		return response;
	}

	// GENERATE PRODUCT CODE
	public String generateProductCode() {

		return "PDT" + String.format("%03d", counter.getAndIncrement());
	}
}
