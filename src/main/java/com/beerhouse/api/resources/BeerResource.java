package com.beerhouse.api.resources;

import java.math.BigDecimal;

import org.springframework.hateoas.ResourceSupport;

import com.beerhouse.domain.Beer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BeerResource extends ResourceSupport {
	
	private final Long id;
	private final String name;
	private final String ingredients;
    private final String alcoholContent;
    private final BigDecimal price;
    private final String category;
    
	public BeerResource(Beer beer) {
		this.id = beer.getId();
		this.name = beer.getName();
		this.ingredients = beer.getIngredients();
		this.alcoholContent = beer.getAlcoholContent();
		this.price = beer.getPrice();
		this.category = beer.getCategory();
	}
	
	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public String getAlcoholContent() {
		return alcoholContent;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}  
    
}
