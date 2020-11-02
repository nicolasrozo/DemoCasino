package com.nirobe.springboot.Casinodemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ErrorTypeException  extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ErrorTypeException(String message){
    	super(message);
	}
}
