package com.ust.userwebapp.web.exception;

public final class UserWebAppBadRequestException extends BaseUserWebAppException {

    public UserWebAppBadRequestException() {
        super();
    }

    public UserWebAppBadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserWebAppBadRequestException(final String message) {
        super(message);
    }

    public UserWebAppBadRequestException(final Throwable cause) {
        super(cause);
    }

}
