package com.nexters.keyme.global.exceptions;

import com.nexters.keyme.global.exceptions.code.ErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KeymeException extends RuntimeException {
    private ErrorCode code;
    private String message;
}