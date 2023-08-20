package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.code.ErrorCode;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND.getMessage());
    }
}
