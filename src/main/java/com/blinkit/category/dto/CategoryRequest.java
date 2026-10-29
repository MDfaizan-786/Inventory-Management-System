package com.blinkit.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


//DTO means Data Transfer Object

public class CategoryRequest {
	
	@NotBlank(message = "Category name is required")
	@Size(max = 100 , message = "Category name cannot exceed 100 characters")
	private String name;
	
	@NotBlank(message = "Category Description is required")
	@Size(max = 1000 , message = "Category Description cannot exceed 1000 characters")
	private String description;
	

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
