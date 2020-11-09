package com.beerhouse.service;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.api.resources.mappers.BeerResourceMapper;
import com.beerhouse.domain.repository.BeerRepository;

import javassist.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerServiceTests {
	
	@Autowired
	private BeerService service;
	
	@Mock
	private BeerRepository repository;
	
	@Mock
	private BeerResourceMapper mappper;
	
	@Test(expected = NotFoundException.class)
	public void should_throw_an_exception_if_it_finds_no_record() throws NotFoundException {
		when(repository.findAll()).thenReturn(asList());
		service.findAll();
	}

}
