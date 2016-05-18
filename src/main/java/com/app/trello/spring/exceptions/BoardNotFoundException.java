package com.app.trello.spring.exceptions;

public class BoardNotFoundException extends Exception {
	
	private String message;
	
	public BoardNotFoundException() {
		// TODO Auto-generated constructor stub
		message = "Board Not Found";
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
}
