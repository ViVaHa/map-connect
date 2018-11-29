package com.fedex.ims.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(final String message) {
		super(message);
	}

	public DataNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
}