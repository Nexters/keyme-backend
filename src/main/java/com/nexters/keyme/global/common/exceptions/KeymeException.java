package com.nexters.keyme.global.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class KeymeException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
    private final int code;
}