package com.beerhouse.api.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeerResource extends ResourceSupport {
	
	private final Long id;
	private final String name;
	private final String ingredients;
    private final String alcoholContent;
    private final BigDecimal price;
    private final String category;
    private final Boolean active;
	private final LocalDateTime createOn;
	private final LocalDateTime modifyOn;
    
	public BeerResource(Long id, String name, String ingredients, String alcoholContent, BigDecimal price,
			String category, Boolean active, LocalDateTime createOn, LocalDateTime modifyOn) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.alcoholContent = alcoholContent;
		this.price = price;
		this.category = category;
		this.active = active;
		this.createOn = createOn;
		this.modifyOn = modifyOn;
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

	public Boolean getActive() {
		return active;
	}

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public LocalDateTime getModifyOn() {
		return modifyOn;
	}
	
}
