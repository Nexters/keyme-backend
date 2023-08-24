package com.nexters.keyme.domain.test.domain.exceptions;

import com.nexters.keyme.domain.test.domain.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeNotFoundException;

public class NotFoundTestException extends KeymeNotFoundException {
    public NotFoundTestException() {
        super(TestErrorCode.NOT_FOUND_TEST.getMessage(), TestErrorCode.NOT_FOUND_TEST.getCode());
    }
}
