package com.beerhouse.api.resources.mappers.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.api.resources.mappers.BeerResourceMapper;
import com.beerhouse.domain.Beer;

@Component
public class BeerResourceMapperImpl implements BeerResourceMapper {
	
	@Autowired
	private EntityLinks entityLinks;
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";

	@Override
	public BeerResource toResource(Beer beer) {
		BeerResource resource = new BeerResource(beer.getId(), beer.getName(), beer.getIngredients(), beer.getAlcoholContent(), 
				beer.getPrice(), beer.getCategory(), beer.getActive(), beer.getCreateOn(), beer.getModifyOn());
		final Link selfLink = entityLinks.linkToSingleResource(beer);
		resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE));
        resource.add(selfLink.withRel(DELETE));
        
		return resource;
	}

	@Override
	public List<BeerResource> toResourceList(List<Beer> beers) {
		return beers.stream()
				.map(beer -> toResource(beer))
				.collect(Collectors.toList());
	}

}
