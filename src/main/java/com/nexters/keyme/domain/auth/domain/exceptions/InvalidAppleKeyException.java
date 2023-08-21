package com.nexters.keyme.domain.auth.domain.exceptions;

import com.nexters.keyme.domain.auth.domain.exceptions.code.AuthErrorCode;
import com.nexters.keyme.global.exceptions.KeymeBadRequestException;

public class InvalidAppleKeyException extends KeymeBadRequestException {
    public InvalidAppleKeyException() {
        super(AuthErrorCode.INVALID_APPLE_KEY.getMessage(), AuthErrorCode.INVALID_APPLE_KEY.getCode());
    }
}
