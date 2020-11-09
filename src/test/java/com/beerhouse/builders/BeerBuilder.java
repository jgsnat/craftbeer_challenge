package com.beerhouse.builders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.beerhouse.domain.Beer;

public class BeerBuilder {
	
	public static Beer getOneBeer(Long id) {
		Beer beer = new Beer();
		beer.setId(id);
		beer.setName("beer test");
		beer.setIngredients("wheat, barley and strawberries...");
		beer.setAlcoholContent("6%");
		beer.setPrice(BigDecimal.valueOf(1.99));
		beer.setCategory("common");
		beer.setCreateOn(LocalDateTime.now());
		beer.setModifyOn(LocalDateTime.now());
		
		return beer;
	}
	
	public static List<Beer> getListBeer(int size) {
		List<Beer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(getOneBeer(Long.valueOf(i)));
		}
		
		return list;
	}

}
