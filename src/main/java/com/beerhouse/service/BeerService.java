package com.beerhouse.service;

import java.util.Collection;

import com.beerhouse.api.resources.BeerResource;

import javassist.NotFoundException;

public interface BeerService {
	
	Collection<BeerResource> findAll() throws NotFoundException;

}
