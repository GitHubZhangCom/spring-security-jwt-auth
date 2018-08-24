package com.jwt.server.exception;

/**
 * token异常
 * @author wb0024
 *
 */
public class TokenException extends BaseException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }
}
