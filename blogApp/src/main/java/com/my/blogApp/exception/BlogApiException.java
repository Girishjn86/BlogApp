package com.my.blogApp.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends Exception{

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	public BlogApiException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public BlogApiException(HttpStatus status, String message, String messageToSuper) {
		super(messageToSuper);
		this.status = status;
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}	
}
