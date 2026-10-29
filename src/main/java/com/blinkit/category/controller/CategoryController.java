package com.blinkit.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.blinkit.category.dto.CategoryRequest;
import com.blinkit.category.dto.CategoryResponse;
import com.blinkit.category.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// CREATE
	@PostMapping("/create")
	public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategories(request));
	}

	// READ ALL
	@GetMapping("/readAll")
	public List<CategoryResponse> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	
	// READ BY ID
	@GetMapping("/readbyId/{id}")
	public CategoryResponse getCategoryById(@PathVariable Long id) {
	    return categoryService.getCategoryById(id);
	}

	// UPDATE
	@PutMapping("/update/{id}")
	public CategoryResponse updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {

		return categoryService.updateCategory(id, request);
	}

	// DELETE
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

		categoryService.deleteCategory(id);

		return ResponseEntity.noContent().build();
	}
}
/*
 * In Spring Data JPA, @Valid is used to trigger validation of an entity or
 * DTO's fields before processing the data, usually when saving or updating
 * records through a REST API.
 */
