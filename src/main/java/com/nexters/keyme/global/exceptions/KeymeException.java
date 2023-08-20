package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class KeymeException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
    private final int code;
}