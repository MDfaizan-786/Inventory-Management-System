package com.blinkit.products.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;

//Create a reusable group of fields
@Embeddable //This class is a group of fields that can be stored as part of another entity's database table."
public class ProductDimensions {

	@Positive
	@Column(precision = 6 , scale = 2)
	private BigDecimal lengthcm;
	@Positive
	@Column(precision = 6 , scale = 2)
	private BigDecimal widthcm;
	@Positive
	@Column(precision = 6 , scale = 2)
	private BigDecimal heightcm;
	
	public ProductDimensions() {
    }
	
	public BigDecimal getLengthcm() {
		return lengthcm;
	}
	public BigDecimal getWidthcm() {
		return widthcm;
	}
	public BigDecimal getHeightcm() {
		return heightcm;
	}
	public void setLengthcm(BigDecimal lengthcm) {
		this.lengthcm = lengthcm;
	}
	public void setWidthcm(BigDecimal widthcm) {
		this.widthcm = widthcm;
	}
	public void setHeightcm(BigDecimal heightcm) {
		this.heightcm = heightcm;
	}
	
	
	
	
}




/*ProductEntity directly create fields to map in database length, bredth, height but
 *  This works. But imagine several classes need these same three fields.
 * 
 *  You would have to repeat the fields in every class.
 *  
 *  */
 