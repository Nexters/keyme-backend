package com.nexters.keyme.domain.test.domain.exceptions;

import com.nexters.keyme.domain.test.domain.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.exceptions.KeymeBadRequestException;

public class AlreadyExistTestResultException extends KeymeBadRequestException {
    public AlreadyExistTestResultException() {
        super(TestErrorCode.ALREADY_EXIST_TEST_RESULT.getMessage(), TestErrorCode.ALREADY_EXIST_TEST_RESULT.getCode());
    }
}