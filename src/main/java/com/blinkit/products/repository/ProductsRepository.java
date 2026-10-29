package com.blinkit.products.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blinkit.products.entity.ProductsEntity;
import com.blinkit.products.enums.ProductsEnum;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

	/*
	 * Derived query methods are methods where Spring Data JPA automatically
	 * generates the database query from the method name.find means retrieve
	 * records. SELECT * FROM products WHERE name = 'Gaming Laptop';
	 * 
	 * By introduces the filter condition.
	 * 
	 * Name refers to the name property in ProductsEntity.
	 * Tum method ka naam likhte ho, aur Spring Data JPA us naam ko parse karke automatically query banata hai.
	 */
	// Find by exact name
	List<ProductsEntity> findByName(String name);

	// Find products within a price range
	List<ProductsEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

	// Find products by status
	List<ProductsEntity> findByStatus(ProductsEnum status);

	// Sort by price from low to high
	List<ProductsEntity> findAllByOrderByPriceAsc();

	// @Query -> @Query is an annotation provided by Spring Data JPA that
	// allows us to define a custom database query directly inside a repository
	// interface.   

	/*
	 * @Query — defines a custom query.
	 * 
	 * SELECT p — selects the entity represented by the alias p.
	 * 
	 * FROM ProductsEntity p — specifies the entity class and its alias.
	 * 
	 * WHERE p.price > 1000 — filters products whose price is greater than 1000.
	 */
	@Query("SELECT p FROM ProductsEntity p WHERE p.price > 1000")
	List<ProductsEntity> findExpensiveProducts();
}

/**/