package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.code.ErrorCode;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED.getMessage());
    }
}
