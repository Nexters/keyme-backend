package com.nexters.keyme.common.exceptions;

import com.nexters.keyme.common.exceptions.code.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class KeymeException extends RuntimeException {
    private ErrorCode code;
    private String message;
}