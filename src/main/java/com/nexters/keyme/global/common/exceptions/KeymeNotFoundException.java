package com.nexters.keyme.global.common.exceptions;

import org.springframework.http.HttpStatus;

public class KeymeNotFoundException extends KeymeException{
    public KeymeNotFoundException(String message, int code) {
        super(HttpStatus.OK, message, code);
    }
}
