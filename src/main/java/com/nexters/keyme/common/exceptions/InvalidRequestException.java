package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super(ErrorCode.INVALID_REQUEST.getMessage());
    }
}
