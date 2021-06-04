package com.innobright.ws.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
//	@ExceptionHandler(value = Exception.class)
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		CustomExceptionResponse exeResp = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exeResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@ExceptionHandler(EmployeeNotFoundException.class)
//	public final ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
//		CustomExceptionResponse exeResp = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
//		return new ResponseEntity<Object>(exeResp, HttpStatus.NOT_FOUND);
//	}
	
//	we can handle more than one exception at a time like below.
	@ExceptionHandler({EmployeeNotFoundException.class, NullPointerException.class})
	public final ResponseEntity<Object> handleEmployeeNotFoundAndNullException(Exception ex, WebRequest request) {
		CustomExceptionResponse exeResp = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exeResp, HttpStatus.NOT_FOUND);
	}

}
