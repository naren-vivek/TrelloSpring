package com.app.trello.spring.exceptions;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ResponseEntity<Object> handleException(DataAccessResourceFailureException ex) {
		return new ResponseEntity<Object>("Can't connect to Database", HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BoardNotFoundException.class)
	public ResponseEntity<Object> handleException(BoardNotFoundException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
