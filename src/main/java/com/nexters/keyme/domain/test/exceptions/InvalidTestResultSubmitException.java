package com.nexters.keyme.domain.test.exceptions;

import com.nexters.keyme.domain.test.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeBadRequestException;

public class InvalidTestResultSubmitException extends KeymeBadRequestException {
    public InvalidTestResultSubmitException() {
        super(TestErrorCode.INVALID_TEST_RESULT_SUBMIT.getMessage(), TestErrorCode.INVALID_TEST_RESULT_SUBMIT.getCode());
    }
}
