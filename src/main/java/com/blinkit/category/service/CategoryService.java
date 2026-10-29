package com.blinkit.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.blinkit.category.dto.CategoryRequest;
import com.blinkit.category.dto.CategoryResponse;
import com.blinkit.category.entity.CategoryEntity;
import com.blinkit.category.repository.CategoryRepository;
import com.blinkit.products.entity.ProductsEntity;
import com.blinkit.products.repository.ProductsRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final ProductsRepository productsRepository;

	private final AtomicInteger counter = new AtomicInteger(1);

	public CategoryService(CategoryRepository categoryRepository, ProductsRepository productsRepository) {

		this.categoryRepository = categoryRepository;
		this.productsRepository = productsRepository;
	}

	// CREATE
	public CategoryResponse createCategories(CategoryRequest request) {

		CategoryEntity entity = new CategoryEntity();

		entity.setCategoryCode(generateCategoryCode());
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());

		CategoryEntity savedEntity = categoryRepository.save(entity);

		return toResponse(savedEntity);
	}

	// READ ALL CATEGORIES
	public List<CategoryResponse> getAllCategories() {

	    List<CategoryEntity> categories = categoryRepository.findAll();

	    List<CategoryResponse> responses = new ArrayList<>();

	    for (CategoryEntity category : categories) {
	    	
	        responses.add(toResponse(category));
	    }

	    return responses;
	}

	// READ BY ID
	public CategoryResponse getCategoryById(Long id) {

		CategoryEntity entity = findCategoryOrThrow(id);

		return toResponse(entity);
	}

	// UPDATE
	public CategoryResponse updateCategory(Long id, CategoryRequest request) {

		CategoryEntity entity = findCategoryOrThrow(id);

		entity.setName(request.getName());
		entity.setDescription(request.getDescription());

		CategoryEntity updatedEntity = categoryRepository.save(entity);

		return toResponse(updatedEntity);
	}

	// DELETE CATEGORY
	// DELETE CATEGORY
	public void deleteCategory(Long id) {

	    // Step 1: Find the category
	    CategoryEntity category = categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	    // Step 2: Get all products
	    List<ProductsEntity> products = productsRepository.findAll();

	    // Step 3: Check whether any product belongs to this category
	    for (ProductsEntity product : products) {

	        if (product.getCategory().getId().equals(id)) {
	            throw new RuntimeException(
	                    "Cannot delete category because products exist");
	        }
	    }

	    // Step 4: Delete the category
	    categoryRepository.delete(category);
	}

	// FIND CATEGORY OR RETURN 404
	private CategoryEntity findCategoryOrThrow(Long id) {

		return categoryRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + id));
	}

	// ENTITY TO RESPONSE DTO
	private CategoryResponse toResponse(CategoryEntity entity) {

		CategoryResponse response = new CategoryResponse();

		response.setId(entity.getId());
		response.setCategoryCode(entity.getCategoryCode());
		response.setName(entity.getName());
		response.setDescription(entity.getDescription());

		return response;
	}

	public String generateCategoryCode() {
		return "CAT" + String.format("%03d", counter.getAndIncrement());
	}
}