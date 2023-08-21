package com.nexters.keyme.domain.test.domain.exceptions;

import com.nexters.keyme.domain.test.domain.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.exceptions.KeymeNotFoundException;

public class NotFoundTestResultException extends KeymeNotFoundException {
    public NotFoundTestResultException() {
        super(TestErrorCode.NOT_FOUND_TEST_RESULT.getMessage(), TestErrorCode.NOT_FOUND_TEST_RESULT.getCode());
    }
}
