package com.yash.validationexample.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5441987381622037646L;
	final BindingResult result;
	
	public ValidationException(BindingResult result) {
		this.result = result;
	}
}
