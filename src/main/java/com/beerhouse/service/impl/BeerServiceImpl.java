package com.beerhouse.service.impl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beerhouse.api.dto.BeerDTO;
import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.api.resources.mappers.BeerResourceMapper;
import com.beerhouse.domain.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.service.BeerService;
import com.beerhouse.utils.BusinessRuleException;
import com.beerhouse.utils.GenericStatus;

import javassist.NotFoundException;

@Service
public class BeerServiceImpl implements BeerService {
	
	private BeerRepository repository;
	private BeerResourceMapper mapper;

	@Autowired
	public BeerServiceImpl(BeerRepository repository, BeerResourceMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<BeerResource> findAll() throws NotFoundException {
		List<Beer> result = this.repository.findAll();
		if (isEmpty(result)) {
			throw new NotFoundException("No records found");
		}

		return mapper.toResourceList(result);
	}

	@Override
	public BeerResource findOne(Long id) throws NotFoundException {
		Beer beer = repository.findOne(id);
		if (null == beer) {
			throw new NotFoundException("No records found");
		}
		
		return mapper.toResource(beer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BeerResource create(BeerDTO dto) {
		if (beerExists(dto.getName().trim())) {
			throw new BusinessRuleException(GenericStatus.GENERIC_MESSAGE_STATUS_422 + ": Beer already exists at the base.");
		}
		Beer beer = repository.save(getInstanceBeer(dto));
		
		return mapper.toResource(beer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BeerResource update(Long id, BeerDTO dto) throws NotFoundException {
		Beer beer = getBeer(id);
		
		if (!dto.getName().trim().equalsIgnoreCase(beer.getName().trim())) {
			beer.setName(dto.getName().trim());
		}
		
		if (!dto.getIngredients().trim().equalsIgnoreCase(beer.getIngredients().trim())) {
			beer.setIngredients(dto.getIngredients().trim());
		}
		
		if (!dto.getAlcoholContent().trim().equalsIgnoreCase(beer.getAlcoholContent().trim())) {
			beer.setAlcoholContent(dto.getAlcoholContent().trim());
		}
		
		if (dto.getPrice().compareTo(beer.getPrice()) != 0) {
			beer.setPrice(dto.getPrice());
		}
		
		if (!dto.getCategory().trim().equalsIgnoreCase(beer.getCategory().trim())) {
			beer.setCategory(dto.getCategory().trim());
		}
		beer.setModifyOn(LocalDateTime.now());
		repository.save(beer);		
		
		return mapper.toResource(beer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) throws NotFoundException {
		Beer beer = getBeer(id);
		beer.setActive(Boolean.FALSE);
		beer.setModifyOn(LocalDateTime.now());
		repository.save(beer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BeerResource reactivate(Long id) throws NotFoundException {
		Beer beer = getBeer(id);
		beer.setActive(Boolean.TRUE);
		beer.setModifyOn(LocalDateTime.now());
		repository.save(beer);
		
		return mapper.toResource(beer);
	}
	
	private Beer getBeer(Long id) throws NotFoundException {
		Beer beer = repository.findOne(id);
		if (null == beer) {
			throw new NotFoundException("No records found");
		}
		
		return beer;
	}
	
	private Beer getInstanceBeer(BeerDTO dto) {
		Beer beer = new Beer();
		beer.setName(dto.getName().trim());
		beer.setIngredients(dto.getIngredients().trim());
		beer.setAlcoholContent(dto.getAlcoholContent().trim());
		beer.setPrice(dto.getPrice());
		beer.setCategory(dto.getCategory().trim());
		beer.setActive(Boolean.TRUE);
		beer.setCreateOn(LocalDateTime.now());
		beer.setModifyOn(LocalDateTime.now());
		
		return beer;
	}
	
	private boolean beerExists(String name) {
		return repository.findByName(name.toLowerCase()).isPresent();
	}

}
