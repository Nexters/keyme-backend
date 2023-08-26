package com.nexters.keyme.domain.test.exceptions;

import com.nexters.keyme.domain.test.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeNotFoundException;

public class NotFoundTestResultException extends KeymeNotFoundException {
    public NotFoundTestResultException() {
        super(TestErrorCode.NOT_FOUND_TEST_RESULT.getMessage(), TestErrorCode.NOT_FOUND_TEST_RESULT.getCode());
    }
}
