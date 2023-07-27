package com.nexters.keyme.common.exceptions;

public class AuthorizationFailedException extends RuntimeException {
    public AuthorizationFailedException() {
        super(ErrorCode.UNAUTHORIZED.message());
    }

    public AuthorizationFailedException(ErrorCode errorCode) {
        super(errorCode.message());
    }
}
