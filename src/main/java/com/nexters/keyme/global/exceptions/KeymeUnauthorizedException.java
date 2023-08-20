package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.KeymeException;
import com.nexters.keyme.global.exceptions.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class KeymeUnauthorizedException extends KeymeException {
    public KeymeUnauthorizedException(String message, int code) {
        super(HttpStatus.UNAUTHORIZED, message, code);
    }
    public KeymeUnauthorizedException(ErrorCode errorCode) {
        super(HttpStatus.UNAUTHORIZED, errorCode.getMessage(), errorCode.getCode());
    }
}
