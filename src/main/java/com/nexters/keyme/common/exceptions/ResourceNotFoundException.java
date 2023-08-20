package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.code.ErrorCode;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND.getMessage());
    }
}
