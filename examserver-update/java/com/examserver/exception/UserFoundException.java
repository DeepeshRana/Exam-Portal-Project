package com.examserver.exception;

public class UserFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4673741885530942998L;

	public UserFoundException() {

	}

	public UserFoundException(String msg) {
		super(msg);
	}

	public UserFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserFoundException(Throwable cause) {
		super(cause);
	}

}
