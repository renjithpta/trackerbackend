package com.ust.userwebapp.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when user is forbidden to execute specified operation or access specified data.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UserWebAppForbiddenException extends BaseUserWebAppException {

   
	 public UserWebAppForbiddenException() {
	        super();
	    }

	    public UserWebAppForbiddenException(final String message, final Throwable cause) {
	        super(message, cause);
	    }

	    public UserWebAppForbiddenException(final String message) {
	        super(message);
	    }

	    public UserWebAppForbiddenException(final Throwable cause) {
	        super(cause);
	    }
}