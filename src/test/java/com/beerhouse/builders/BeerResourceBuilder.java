package com.beerhouse.builders;

import java.util.ArrayList;
import java.util.List;

import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.domain.Beer;

public class BeerResourceBuilder {
	
	public static BeerResource getOneBeerResource(Beer beer) {
		return new BeerResource(beer.getId(), beer.getName(), beer.getIngredients(), beer.getAlcoholContent(), 
				beer.getPrice(), beer.getCategory(), beer.getActive(), beer.getCreateOn(), beer.getModifyOn());
	}
	
	public static List<BeerResource> getListBeerResource(List<Beer> beers) {
		List<BeerResource> list = new ArrayList<>();
		for (Beer beer : beers) {
			list.add(getOneBeerResource(beer));
		}
		
		return list;
	}

}
