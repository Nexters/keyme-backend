package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.code.ErrorCode;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
        super(ErrorCode.RESOURCE_ALREADY_EXIST.getMessage());
    }
    public ResourceAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
