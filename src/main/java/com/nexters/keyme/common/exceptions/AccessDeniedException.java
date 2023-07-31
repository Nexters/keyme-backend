package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED.message());
    }
}
