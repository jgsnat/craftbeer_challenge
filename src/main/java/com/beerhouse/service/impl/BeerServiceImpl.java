package com.beerhouse.service.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.api.resources.mappers.BeerResourceMapper;
import com.beerhouse.domain.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.service.BeerService;

import javassist.NotFoundException;

@Service
public class BeerServiceImpl implements BeerService {
	
	@Autowired
	private BeerRepository repository;
	
	@Autowired
	private BeerResourceMapper mapper;

	@Override
	public Collection<BeerResource> findAll() throws NotFoundException {
		List<Beer> result = repository.findAll();
		if (isEmpty(result)) {
			throw new NotFoundException("No records found");
		}
		Collection<BeerResource> list = mapper.toResourceCollection(result);
				
		return list;
	}

}
