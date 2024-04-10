package com.example.demo.ecxeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private String resourceName;
	private String fieldName;
	private Object fieValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieValue) {
		super(String.format("%s not found with %s : '%s'",resourceName, fieldName, fieValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieValue = fieValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieValue() {
		return fieValue;
	}

	

}
