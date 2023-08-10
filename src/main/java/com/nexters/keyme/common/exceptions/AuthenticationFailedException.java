package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super(ErrorCode.UNAUTHORIZED.message());
    }

    public AuthenticationFailedException(ErrorCode errorCode) {
        super(errorCode.message());
    }
}
