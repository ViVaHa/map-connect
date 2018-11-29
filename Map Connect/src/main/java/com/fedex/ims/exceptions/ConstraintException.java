package com.fedex.ims.exceptions;

/**
 * Thrown when attempting an action that violates a constraint.
 */
public class ConstraintException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConstraintException(final String message) {
		super(message);
	}

	public ConstraintException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
