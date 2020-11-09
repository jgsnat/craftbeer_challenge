package com.beerhouse.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BeerDTO implements Serializable {

	private static final long serialVersionUID = -6491825740546210756L;

	@NotNull(message = "{beerDTO.name.validation.null}")
	@NotBlank(message = "{beerDTO.name.validation.blank}")
	@Length(max = 50, message = "{beerDTO.name.validation.length}")
	private String name;
	
	@NotNull(message = "{beerDTO.ingredients.validation.null}")
	@NotBlank(message = "{beerDTO.ingredients.validation.blank}")
	@Length(max = 150, message = "{beerDTO.ingredients.validation.length}")
	private String ingredients;
	
	@NotNull(message = "{beerDTO.alcoholContent.validation.null}")
	@NotBlank(message = "{beerDTO.alcoholContent.validation.blank}")
	@Length(max = 20, message = "{beerDTO.alcoholContent.validation.length}")
    private String alcoholContent;
	
	@NotNull(message = "{beerDTO.price.validation.null}")
    private BigDecimal price;
	
	@NotNull(message = "{beerDTO.category.validation.null}")
	@NotBlank(message = "{beerDTO.category.validation.blank}")
	@Length(max = 50, message = "{beerDTO.category.validation.length}")
    private String category;
	
	public BeerDTO() {
		
	}
	
	public BeerDTO(
			@NotNull(message = "{beerDTO.name.validation.null}") @NotBlank(message = "{beerDTO.name.validation.blank}") @Length(max = 50, message = "{beerDTO.name.validation.length}") String name, 
			@NotNull(message = "{beerDTO.ingredients.validation.null}") @NotBlank(message = "{beerDTO.ingredients.validation.blank}") @Length(max = 100, message = "{beerDTO.ingredients.validation.length}") String ingredients, 
			@NotNull(message = "{beerDTO.alcoholContent.validation.null}") @NotBlank(message = "{beerDTO.alcoholContent.validation.blank}") @Length(max = 20, message = "{beerDTO.alcoholContent.validation.length}") String alcoholContent, 
			@NotNull(message = "{beerDTO.price.validation.null}") BigDecimal price, 
			@NotNull(message = "{beerDTO.category.validation.null}") @NotBlank(message = "{beerDTO.category.validation.blank}") @Length(max = 50, message = "{beerDTO.category.validation.length}") String category) {
		this.name = name;
		this.ingredients = ingredients;
		this.alcoholContent = alcoholContent;
		this.price = price;
		this.category = category;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getAlcoholContent() {
		return alcoholContent;
	}
	
	public void setAlcoholContent(String alcoholContent) {
		this.alcoholContent = alcoholContent;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
    
}
