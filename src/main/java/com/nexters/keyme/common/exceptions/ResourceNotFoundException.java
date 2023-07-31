package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND.message());
    }
}
