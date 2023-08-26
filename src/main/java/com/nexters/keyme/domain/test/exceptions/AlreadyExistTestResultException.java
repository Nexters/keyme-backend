package com.nexters.keyme.domain.test.exceptions;

import com.nexters.keyme.domain.test.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeBadRequestException;

public class AlreadyExistTestResultException extends KeymeBadRequestException {
    public AlreadyExistTestResultException() {
        super(TestErrorCode.ALREADY_EXIST_TEST_RESULT.getMessage(), TestErrorCode.ALREADY_EXIST_TEST_RESULT.getCode());
    }
}