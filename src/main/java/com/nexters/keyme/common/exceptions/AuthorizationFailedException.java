package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;

public class AuthorizationFailedException extends RuntimeException {
    public AuthorizationFailedException() {
        super(ErrorCode.UNAUTHORIZED.message());
    }

    public AuthorizationFailedException(ErrorCode errorCode) {
        super(errorCode.message());
    }
}
