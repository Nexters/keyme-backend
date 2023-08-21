package com.nexters.keyme.global.exceptions;

import org.springframework.http.HttpStatus;

public class KeymeBadRequestException extends KeymeException {
    public KeymeBadRequestException(String message, int code) {

        super(HttpStatus.OK, message, code);
    }
}
