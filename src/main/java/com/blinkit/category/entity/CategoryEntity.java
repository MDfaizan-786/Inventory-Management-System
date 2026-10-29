package com.blinkit.category.entity;


import java.util.ArrayList;
import java.util.List;

import com.blinkit.products.entity.ProductsEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity //Tells Java that this class represents data that should be saved in the database.This Java class represents a database table.
@Table(name = "category") //Tells Hibernate which database table the class belongs to.Tells JPA which database table to use.
public class CategoryEntity {//The inverse side is the entity that refers to the owning side using the mappedBy attribute. It does not independently define the foreign key mapping for that relationship.

	@Id //-->Marks a field as the primary key of the table.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue → Tells Hibernate to automatically generate the ID value.
	private Long id;
	
	@NotBlank(message = "name is required")
	@Size(max = 100)
	@Column(nullable = false ,name = "category_name", length = 100) //@Column →Tells JPA how a Java field maps to a database column.
	private String name;
	
	@NotNull(message = "description is required")
	@Size(max = 1000, message = "Description cannot exceed 500 characters")
	@Column(nullable = false , length = 1000)
	private String description;
	
	@NotBlank(message = "Category code is required")
    @Size(max = 20, message = "Category code cannot exceed 20 characters")
	@Column(name = "category_code" ,length = 20, unique = true, nullable = false)
    private String categoryCode;
	

	//Category can contains many products. 
	//category also need list of products
	@OneToMany(mappedBy = "category" , fetch = FetchType.LAZY) //mappedBy tells JPA that this collection is mapped by the field called category in the other entity—in our case, ProductsEntity.
	private List<ProductsEntity> products;
//We use a collection because one category can be associated with multiple 
	//product objects.


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public List<ProductsEntity> getProducts() {
		return products;
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


	public void setProducts(List<ProductsEntity> products) {
		this.products = products;
	}
	
	
	public String getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}

/*mappedBy is an attribute used in JPA to specify the field in the owning entity that manages the relationship. It is used on the inverse side of a bidirectional relationship to avoid defining the same relationship mapping twice.


Because ProductEntity already manages the relationship through category_id. mappedBy tells JPA to use that existing mapping instead of defining another relationship mapping.*/




/*Imagine the Product table already has a category_id column.


java
// ProductEntity
@ManyToOne
@JoinColumn(name = "category_id")
private CategoryEntity category;
Now, in CategoryEntity:


java
@OneToMany(mappedBy = "category")
private List<ProductEntity> products;
How does mappedBy work?

It tells JPA: "Look at the category field in ProductEntity. The relationship is already defined there."

So JPA uses the existing relationship mapping instead of creating another one.


*/















/*JPA Annotations — Very Simple English
@Table → Tells JPA which database table to use.
@Id → Marks the primary key (unique ID) of the table.
@GeneratedValue → Automatically generates the ID value.
@Column → Tells JPA how a Java field maps to a database column.
@Transient → Do not save this field in the database.
@Enumerated(EnumType.STRING) → Save an enum as its name/text in the database.
@Lob → Used for storing large data, such as long text or files.
Easy way to remember
JPA annotations = instructions that tell Java ↔ Database how to map things.

JPA describes the mapping; Hibernate uses that mapping to save, read, update, and delete data in the database.*/
/*
@Column → Used to configure how a Java field maps to a database column.

@Transient → Tells Hibernate not to save this field in the database.

@Enumerated(EnumType.STRING) → Saves an enum value as its name/text in the database instead of a number.

@Lob → Used when a field contains large data, such as a long text or file.

In simple terms:
JPA annotations tell Hibernate how Java classes and their fields should be connected to database tables and columns. 
Hibernate then uses this information to save, update, and retrieve data from the database.*/

