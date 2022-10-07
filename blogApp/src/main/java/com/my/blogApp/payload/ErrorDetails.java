package com.my.blogApp.payload;

import java.util.Date;

public class ErrorDetails {
	Date timestamp;
	String message;
	String description;
	public ErrorDetails(Date date, String message, String description) {
		super();
		this.timestamp = date;
		this.message = message;
		this.description = description;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDescription() {
		return description;
	}
}
