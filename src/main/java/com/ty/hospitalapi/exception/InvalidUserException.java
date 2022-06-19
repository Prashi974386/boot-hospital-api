package com.ty.hospitalapi.exception;

public class InvalidUserException extends RuntimeException {
	private String message = "UserEmail or Password is Wrong";

	public InvalidUserException() {
	}

	public InvalidUserException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
