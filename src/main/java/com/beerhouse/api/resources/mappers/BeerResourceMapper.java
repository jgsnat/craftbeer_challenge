package com.beerhouse.api.resources.mappers;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.domain.Beer;

@Component
public class BeerResourceMapper {
	
	@Autowired
	private EntityLinks entityLinks;
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";
	
	public BeerResource toResource(Beer beer) {
		BeerResource resource = new BeerResource(beer);
		final Link selfLink = entityLinks.linkToSingleResource(beer);
		resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE));
        resource.add(selfLink.withRel(DELETE));
        
		return resource;
	}
	
	public Collection<BeerResource> toResourceCollection(Collection<Beer> objects) {
		return objects.stream()
				.map(b -> toResource(b))
				.collect(Collectors.toList());
	}
	
}
