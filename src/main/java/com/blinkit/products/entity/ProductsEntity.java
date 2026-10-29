package com.blinkit.products.entity;

import java.math.BigDecimal;

import com.blinkit.category.entity.CategoryEntity;
import com.blinkit.products.enums.ProductsEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class ProductsEntity { //Owning side: ProductsEntity  //The owning side is the entity that controls the relationship mapping in the database. It usually contains the @JoinColumn annotation, which specifies the foreign key column.
							//The owning side defines the foreign-key mapping.

	@Id //act as a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@Column is a JPA annotation used to specify the details of a database column mapped to a Java class field.
	@NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
	@Column(name = "product_name" , nullable = false) //length = 100 otherwise by default 255 varchar
	private String name;
	
	@NotBlank(message = "Description of Product is required")
	@Size(max = 1000 , message = "Description Should not exceed 1000")
	private String description;

	@NotNull(message = "Price is required")
	@Positive
	@Column(name = "product_price" ,precision = 12, scale = 2 , nullable = false)
	private BigDecimal price;

	@NotNull(message = "stockQuantity is required")
	@Min(value = 0, message = "stockQuantity cannot be negative")
	@Column(nullable = false )
	private Integer stockQuantity;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING) // Save an enum as its name/text in the database.
	private ProductsEnum status; //@Enumerated means specifying how a Java enum should be stored in the database.
	/*@Enumerated ka matlab: Java ke enum ko database mein kis format mein store karna hai, ye batana.
- EnumType.STRING → Enum ka naam store hoga, jaise "ACTIVE".
- EnumType.ORDINAL → Enum ka number/index store hoga, jaise 0.*/
	
	
	@NotBlank(message = "Product code is required")
    @Size(max = 20, message = "Product code cannot exceed 20 characters")
	@Column(name = "product_code" ,length = 20, unique = true, nullable = false)
    private String productCode;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Transient // Do not save this field in the database.
	private String temporaryDisplayLabel;
	
	@Embedded //  Uses that group inside an entity.
	private ProductDimensions dimensions;

	// many Products belong to one Category
                                         //optional = false: The relationship is mandatory. 
	@ManyToOne(fetch = FetchType.EAGER , optional = false) //@JoinColumn tells JPA which database column(product table's column) stores the foreign key connecting a product to its category.
	@JoinColumn( name = "category_id" , nullable = false) //This identifies the database column used to store the relationship's foreign-key value.In our case, the column is called category_id.
	private CategoryEntity category;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public ProductsEnum getStatus() {
		return status;
	}

	public String getTemporaryDisplayLabel() {
		return temporaryDisplayLabel;
	}

	public ProductDimensions getDimensions() {
		return dimensions;
	}

	public CategoryEntity getCategory() {
		return category;
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

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void setStatus(ProductsEnum status) {
		this.status = status;
	}

	public void setTemporaryDisplayLabel(String temporaryDisplayLabel) {
		this.temporaryDisplayLabel = temporaryDisplayLabel;
	}

	public void setDimensions(ProductDimensions dimensions) {
		this.dimensions = dimensions;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	
}


/*
 * 
 * @Table(uniqueConstraints = ...) sirf UNIQUE constraint define karta hai.
 *  Baaki constraints ke liye alag mapping/annotations chahiye.
 * 
 * @Entity
@Table(
    name = "products",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_products_sku",
            columnNames = {"sku"}
        )
    }
)*/
