package com.nexters.keyme.domain.test.domain.exceptions;

import com.nexters.keyme.domain.test.domain.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.exceptions.KeymeNotFoundException;

public class NotFoundTestResultCodeException extends KeymeNotFoundException {
    public NotFoundTestResultCodeException() {
        super(TestErrorCode.NOT_FOUND_TEST_RESULT_CODE.getMessage(), TestErrorCode.NOT_FOUND_TEST_RESULT_CODE.getCode());
    }
}
