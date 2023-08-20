package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.code.ErrorCode;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super(ErrorCode.INVALID_REQUEST.getMessage());
    }
}
