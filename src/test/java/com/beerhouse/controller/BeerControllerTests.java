package com.beerhouse.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.beerhouse.api.controller.BeerController;
import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.builders.BeerBuilder;
import com.beerhouse.builders.BeerResourceBuilder;
import com.beerhouse.domain.Beer;
import com.beerhouse.service.BeerService;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTests {
	
	private final static String URI_BASE = "/beerhouse/v1/beers";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BeerService service;
	
	@Test
	public void should_return_status_404_if_no_record_is_found() throws Exception {	 
	    this.mockMvc.perform(get(URI_BASE)
    		.contentType(MediaType.APPLICATION_JSON))
	    	.andExpect(status().isNotFound());
	}
	
	@Test
	public void must_return_status_200_if_it_finds_any_record() throws Exception {
		List<Beer> beers = BeerBuilder.getListBeer(1);
		when(service.findAll()).thenReturn(BeerResourceBuilder.getListBeerResource(beers));
	 
	    this.mockMvc.perform(get(URI_BASE)
	    	.contentType(MediaType.APPLICATION_JSON))
	    	.andExpect(status().isOk());
	}

}
