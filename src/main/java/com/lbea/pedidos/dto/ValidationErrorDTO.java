package com.lbea.pedidos.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrorDTO extends CustomErrorDTO{
	
	private List<FieldMessageDTO> errors = new ArrayList<>();

	public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
		// TODO Auto-generated constructor stub
	}
	
	 public void addError(String fieldName, String message) {
	    	errors.removeIf(x -> x.getFieldName().equals(fieldName));
	        errors.add(new FieldMessageDTO(fieldName, message));
	    }

}
