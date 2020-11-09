package com.beerhouse.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.api.dto.BeerDTO;
import com.beerhouse.api.errors.ErrorResponse;
import com.beerhouse.api.resources.BeerResource;
import com.beerhouse.domain.Beer;
import com.beerhouse.service.BeerService;
import com.beerhouse.utils.GenericStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@Api(value = "Responsible API for craft beer management")
@ExposesResourceFor(Beer.class)
@RequestMapping(value = "v1/beers", produces = "application/json")
public class BeerController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BeerController.class);
	
	@Autowired
	private BeerService service;
	
	@ApiOperation(value = "Responsible for listing all beers saved in the base")
	@ApiResponses(
		value = {
			@ApiResponse(
				code = 200,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_200,
				response = BeerResource[].class 
			),
			@ApiResponse(
				code = 500,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_500
			),
			@ApiResponse(
				code = 404,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_404
			)
		}
	)
	@GetMapping
	public ResponseEntity<List<BeerResource>> getAll() throws NotFoundException {
		LOG.info("Calling the method: BeerController.getAll()");
		List<BeerResource> resources = service.findAll();
		LOG.info("method: BeerController.getAll() successfully executed");
		
		return ResponseEntity.ok(resources);
	}
	
	@ApiOperation(value = "Responsible for listing a beer saved in the base according to its id")
	@ApiResponses(
		value = {
			@ApiResponse(
				code = 200,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_200,
				response = BeerResource.class 
			),
			@ApiResponse(
				code = 500,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_500
			),
			@ApiResponse(
				code = 404,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_404
			)
		}
	)
	@GetMapping(path = "/{id}")
	public ResponseEntity<BeerResource> getOne(@PathVariable("id") Long id) throws NotFoundException {
		LOG.info("Calling the method: BeerController.getOne()");
		BeerResource resource = service.findOne(id);
		LOG.info("method: BeerController.getOne() successfully executed");
		
		return ResponseEntity.ok(resource);
	}
	
	@ApiOperation(value = "Responsible for registering a beer at the base")
	@ApiResponses(
		value = {
			@ApiResponse(
				code = 201,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_201,
				response = BeerResource.class 
			),
			@ApiResponse(
				code = 500,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_500
			),
			@ApiResponse(
				code = 400,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_400,
				response = ErrorResponse.class
			),
			@ApiResponse(
				code = 422,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_422,
				response = ErrorResponse.class
			)
		}
	)
	@PostMapping(consumes = "application/json")
	public ResponseEntity<BeerResource> create(
			@ApiParam(value = "Body of the object to register a beer")
			@RequestBody @Valid BeerDTO dto) throws NotFoundException {
		LOG.info("Calling the method: BeerController.create()");
		BeerResource resource = service.create(dto);
		LOG.info("method: BeerController.create() successfully executed");
		
		return new ResponseEntity<BeerResource>(resource, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Responsible for updating a beer in the base")
	@ApiResponses(
		value = {
			@ApiResponse(
				code = 200,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_200,
				response = BeerResource.class 
			),
			@ApiResponse(
				code = 500,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_500
			),
			@ApiResponse(
				code = 400,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_400,
				response = ErrorResponse.class
			),
			@ApiResponse(
				code = 404,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_404
			)
		}
	)
	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<BeerResource> update(
			@ApiParam(value = "Body of the object to updating a beer")
			@PathVariable("id") Long id,
			@RequestBody @Valid BeerDTO dto) throws NotFoundException {
		LOG.info("Calling the method: BeerController.update()");
		BeerResource resource = service.update(id, dto);
		LOG.info("method: BeerController.update() successfully executed");
		
		return ResponseEntity.ok(resource);
	}
	
	@ApiOperation(value = "Responsible for activating a beer at the base of the system (logical exclusion)")
	@ApiResponses(
		value = {
			@ApiResponse(
				code = 200,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_200,
				response = BeerResource.class 
			),
			@ApiResponse(
				code = 500,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_500
			),
			@ApiResponse(
				code = 404,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_404
			)
		}
	)
	@PatchMapping(path = "/{id}")
	public ResponseEntity<BeerResource> reactivate(@PathVariable("id") Long id) throws NotFoundException {
		LOG.info("Calling the method: BeerController.reactivate()");
		BeerResource resource = service.reactivate(id);
		LOG.info("method: BeerController.reactivate() successfully executed");
		
		return ResponseEntity.ok(resource);
	}
	
	@ApiOperation(value = "Responsible for excluding a beer at the base of the system (logical exclusion)")
	@ApiResponses(
		value = {
			@ApiResponse(
				code = 204,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_204
			),
			@ApiResponse(
				code = 500,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_500
			),
			@ApiResponse(
				code = 404,
				message = GenericStatus.GENERIC_MESSAGE_STATUS_404
			)
		}
	)
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<BeerResource> delete(@PathVariable("id") Long id) throws NotFoundException {
		LOG.info("Calling the method: BeerController.delete()");
		service.delete(id);
		LOG.info("method: BeerController.delete() successfully executed");
		
		return ResponseEntity.noContent().build();
	}

}
