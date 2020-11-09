package com.beerhouse.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.hateoas.Identifiable;

@Entity
public class Beer implements Identifiable<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beer_sequence")
	@SequenceGenerator(name = "beer_sequence", sequenceName = "ber_seq")
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 150, nullable = false)
	private String ingredients;
	
	@Column(length = 20, name = "alcohol_content", nullable = false)
    private String alcoholContent;
	
	@Column(scale = 2, precision = 18, nullable = false)
    private BigDecimal price;
	
	@Column(length = 50, nullable = false)
    private String category;
	
	@Column(nullable = false)
	private Boolean active;
	
	@Column(name = "create_on", nullable = false)
	private LocalDateTime createOn;

	@Column(name = "modify_on", nullable = false)
	private LocalDateTime modifyOn;
    
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}

	public LocalDateTime getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(LocalDateTime modifyOn) {
		this.modifyOn = modifyOn;
	}
	
}
