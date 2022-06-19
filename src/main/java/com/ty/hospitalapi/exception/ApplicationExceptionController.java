package com.ty.hospitalapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ty.hospitalapi.dto.ResponseStracture;

@ControllerAdvice
public class ApplicationExceptionController {
	@ExceptionHandler(value = NoIDFoundException.class)
	public ResponseEntity<ResponseStracture<String>> noIdFoundExceptionHandle(NoIDFoundException ie){
		ResponseStracture<String> responseStracture = new ResponseStracture<String>();
		responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStracture.setMessage("No data found");
		responseStracture.setData(ie.getMessage());
		
		return new ResponseEntity<ResponseStracture<String>>(responseStracture,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidUserException.class)
	public ResponseEntity<ResponseStracture<String>> invalidUserExceptionHandle(InvalidUserException ie){
		ResponseStracture<String> responseStracture = new ResponseStracture<String>();
		responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStracture.setMessage("email or password not found");
		responseStracture.setData(ie.getMessage());
		
		return new ResponseEntity<ResponseStracture<String>>(responseStracture,HttpStatus.NOT_FOUND);
	}
}
