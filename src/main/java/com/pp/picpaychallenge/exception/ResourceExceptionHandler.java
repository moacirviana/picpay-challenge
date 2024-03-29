package com.pp.picpaychallenge.exception;

import java.time.LocalDateTime;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
    	StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
	
	@ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
    	StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage() + " DI", LocalDateTime.now());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> generalError(Exception e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage() + " GN", LocalDateTime.now());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }	
	
	
	
}