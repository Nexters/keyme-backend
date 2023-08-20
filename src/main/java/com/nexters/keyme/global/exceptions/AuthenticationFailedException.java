package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.code.ErrorCode;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super(ErrorCode.UNAUTHORIZED.getMessage());
    }

    public AuthenticationFailedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
