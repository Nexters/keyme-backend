package com.nexters.keyme.domain.test.exceptions;

import com.nexters.keyme.domain.test.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeNotFoundException;

public class NotFoundTestResultCodeException extends KeymeNotFoundException {
    public NotFoundTestResultCodeException() {
        super(TestErrorCode.NOT_FOUND_TEST_RESULT_CODE.getMessage(), TestErrorCode.NOT_FOUND_TEST_RESULT_CODE.getCode());
    }
}
