package com.nexters.keyme.domain.test.exceptions;

import com.nexters.keyme.domain.test.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeBadRequestException;

public class InvalidDailyTestException extends KeymeBadRequestException {
    public InvalidDailyTestException() {
        super(TestErrorCode.INVALID_DAILY_TEST.getMessage(), TestErrorCode.INVALID_DAILY_TEST.getCode());
    }
}
