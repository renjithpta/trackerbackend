package com.ust.userwebapp.web.exception;

public final class UserWebAppResourceNotFoundException extends BaseUserWebAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	 public UserWebAppResourceNotFoundException() {
	        super();
	    }

	    public UserWebAppResourceNotFoundException(final String message, final Throwable cause) {
	        super(message, cause);
	    }

	    public UserWebAppResourceNotFoundException(final String message) {
	        super(message);
	    }

	    public UserWebAppResourceNotFoundException(final Throwable cause) {
	        super(cause);
	    }
 

}
