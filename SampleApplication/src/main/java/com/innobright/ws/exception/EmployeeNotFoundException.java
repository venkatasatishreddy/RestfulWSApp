package com.innobright.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//value is alias name for code, it means we can use either value or code attribute 
//but not possible to use both together.
//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such data")
//@ResponseStatus(code = HttpStatus.NOT_FOUND)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public EmployeeNotFoundException(String message) {
		this.message = message;		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
