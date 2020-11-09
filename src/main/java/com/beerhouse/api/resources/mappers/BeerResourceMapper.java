package com.beerhouse.api.resources.mappers;

import java.util.List;

import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.domain.Beer;

public interface BeerResourceMapper {
	
	BeerResource toResource(Beer beer);
	
	List<BeerResource> toResourceList(List<Beer> beers);
	
}
