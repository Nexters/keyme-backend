package com.nexters.keyme.global.common.exceptions;

import org.springframework.http.HttpStatus;

public class KeymeSuccessInfoException extends KeymeException{
    public KeymeSuccessInfoException(String message, int code) {
        super(HttpStatus.OK, message, code);
    }
}
