package com.beerhouse.utils;

public class BusinessRuleException extends RuntimeException {
	
	private static final long serialVersionUID = -8541500775613069569L;

	public BusinessRuleException(String errorMessage) {
		super(errorMessage);
	}
	
}
