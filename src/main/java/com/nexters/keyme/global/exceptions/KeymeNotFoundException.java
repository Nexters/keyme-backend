package com.nexters.keyme.global.exceptions;

import org.springframework.http.HttpStatus;

public class KeymeNotFoundException extends KeymeException{
    public KeymeNotFoundException(String message, int code) {
        super(HttpStatus.BAD_REQUEST, message, code);
    }
}
