package com.beerhouse.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.beerhouse.api.errors.ErrorObject;
import com.beerhouse.api.errors.ErrorResponse;
import com.beerhouse.utils.BusinessRuleException;

import javassist.NotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		List<ErrorObject> errors = getErrorsArgumentsNotValid(ex);
		ErrorResponse errorResponse = getErrorResponseArgumentsNotValid(status, errors);
		
		return new ResponseEntity<>(errorResponse, status);
	}

	private ErrorResponse getErrorResponseArgumentsNotValid(HttpStatus status, List<ErrorObject> errors) {
		return new ErrorResponse("The request has invalid fields", status.value(), status.getReasonPhrase(), errors);
	}
	
	private List<ErrorObject> getErrorsArgumentsNotValid(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
				.collect(Collectors.toList());
	}
	
	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ BusinessRuleException.class })
	public ResponseEntity<Object> handleRegraNegocioException(BusinessRuleException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
