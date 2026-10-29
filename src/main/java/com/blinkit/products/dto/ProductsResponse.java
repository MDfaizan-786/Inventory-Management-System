package com.blinkit.products.dto;

import java.math.BigDecimal;

import com.blinkit.products.entity.ProductDimensions;
import com.blinkit.products.enums.ProductsEnum;

public class ProductsResponse {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stockQuantity;
	private ProductsEnum status;
	
	private Long categoryId;
	
	private String categoryName;
	
	private String productCode;

	  // Product dimensions
    private ProductDimensions dimensions;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public ProductsEnum getStatus() {
		return status;
	}

	public void setStatus(ProductsEnum status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ProductDimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(ProductDimensions dimensions) {
		this.dimensions = dimensions;
	}
}
