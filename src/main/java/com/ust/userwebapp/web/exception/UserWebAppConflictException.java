package com.ust.userwebapp.web.exception;

public final class UserWebAppConflictException extends BaseUserWebAppException {

	 public UserWebAppConflictException() {
	        super();
	    }

	    public UserWebAppConflictException(final String message, final Throwable cause) {
	        super(message, cause);
	    }

	    public UserWebAppConflictException(final String message) {
	        super(message);
	    }

	    public UserWebAppConflictException(final Throwable cause) {
	        super(cause);
	    }


}
