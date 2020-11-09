package com.beerhouse.service;

import java.util.List;

import com.beerhouse.api.dto.BeerDTO;
import com.beerhouse.api.resources.BeerResource;

import javassist.NotFoundException;

public interface BeerService {
	
	List<BeerResource> findAll() throws NotFoundException;
	
	BeerResource findOne(Long id) throws NotFoundException;
	
	BeerResource create(BeerDTO dto);
	
	BeerResource update(Long id, BeerDTO dto) throws NotFoundException;
	
	void delete(Long id) throws NotFoundException;
	
	BeerResource reactivate(Long id) throws NotFoundException;

}
