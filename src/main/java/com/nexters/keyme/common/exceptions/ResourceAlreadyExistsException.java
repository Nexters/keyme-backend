package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException() {
        super(ErrorCode.RESOURCE_ALREADY_EXIST.message());
    }
    public ResourceAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode.message());
    }
}
