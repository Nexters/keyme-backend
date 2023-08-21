package com.nexters.keyme.domain.test.domain.exceptions;

import com.nexters.keyme.domain.test.domain.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.exceptions.KeymeBadRequestException;

public class InvalidDailyTestException extends KeymeBadRequestException {
    public InvalidDailyTestException() {
        super(TestErrorCode.INVALID_DAILY_TEST.getMessage(), TestErrorCode.INVALID_DAILY_TEST.getCode());
    }
}
