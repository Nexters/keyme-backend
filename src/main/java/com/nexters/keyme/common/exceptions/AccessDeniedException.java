package com.nexters.keyme.common.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED.message());
    }
}
