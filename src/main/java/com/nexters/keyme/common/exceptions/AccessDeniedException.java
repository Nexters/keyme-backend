package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.code.ErrorCode;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED.getMessage());
    }
}
