package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
        super(ErrorCode.RESOURCE_ALREADY_EXIST.getMessage());
    }
    public ResourceAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
