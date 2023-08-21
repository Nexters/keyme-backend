package com.nexters.keyme.global.exceptions;

import org.springframework.http.HttpStatus;

public class KeymeSuccessInfoException extends KeymeException{
    public KeymeSuccessInfoException(String message, int code) {
        super(HttpStatus.OK, message, code);
    }
}
