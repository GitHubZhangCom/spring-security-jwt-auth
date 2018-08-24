package com.jwt.server.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAuthException extends AuthenticationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserAuthException(String msg) {
        super(msg);
    }
}
