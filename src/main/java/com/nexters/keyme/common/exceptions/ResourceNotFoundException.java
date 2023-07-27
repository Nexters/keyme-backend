package com.nexters.keyme.common.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND.message());
    }
}
