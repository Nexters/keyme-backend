package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.code.ErrorCode;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super(ErrorCode.INVALID_REQUEST.getMessage());
    }
}
