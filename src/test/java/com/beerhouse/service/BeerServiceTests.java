package com.beerhouse.service;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beerhouse.domain.repository.BeerRepository;

import javassist.NotFoundException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BeerServiceTests {
	
	private static final Logger LOG = LoggerFactory.getLogger(BeerServiceTests.class);
	
	@Autowired
	private BeerService service;
	
	@Mock
	private BeerRepository repository;
	
	@Test(expected = NotFoundException.class)
	public void noRecordTestFound() throws NotFoundException {
		LOG.info("Testing noRecordTestFound()");
		when(repository.findAll()).thenReturn(null);
		service.findAll();
	}

}
