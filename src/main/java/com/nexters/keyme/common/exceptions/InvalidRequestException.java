package com.nexters.keyme.common.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super(ErrorCode.INVALID_REQUEST.message());
    }
}
