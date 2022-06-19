package com.ty.hospitalapi.exception;

public class NoIDFoundException extends RuntimeException {
	private String message = "Id is not Exist";

	public NoIDFoundException() {
	}

	public NoIDFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
