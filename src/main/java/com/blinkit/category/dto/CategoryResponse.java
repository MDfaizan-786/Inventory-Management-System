package com.blinkit.category.dto;

import java.util.List;

import com.blinkit.products.entity.ProductsEntity;

public class CategoryResponse {

	
	private Long id;
	private String name;
	private String categoryCode;
	private String description;
	
	
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	
	
	
}
