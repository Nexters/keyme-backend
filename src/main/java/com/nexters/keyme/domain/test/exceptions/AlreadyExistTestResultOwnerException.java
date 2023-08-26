package com.nexters.keyme.domain.test.exceptions;

import com.nexters.keyme.domain.test.exceptions.code.TestErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeBadRequestException;

public class AlreadyExistTestResultOwnerException extends KeymeBadRequestException {
    public AlreadyExistTestResultOwnerException() {
        super(TestErrorCode.ALREADY_EXIST_TEST_RESULT_OWNER.getMessage(), TestErrorCode.ALREADY_EXIST_TEST_RESULT_OWNER.getCode());
    }
}
