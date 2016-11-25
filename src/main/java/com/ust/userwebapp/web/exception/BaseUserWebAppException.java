package com.ust.userwebapp.web.exception;

public class BaseUserWebAppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public BaseUserWebAppException() {
	        super();
	    }

	    public BaseUserWebAppException(final String message, final Throwable cause) {
	        super(message, cause);
	    }

	    public BaseUserWebAppException(final String message) {
	        super(message);
	    }

	    public BaseUserWebAppException(final Throwable cause) {
	        super(cause);
	    }


}
