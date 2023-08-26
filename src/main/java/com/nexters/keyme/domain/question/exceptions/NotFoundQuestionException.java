package com.nexters.keyme.domain.question.exceptions;

import com.nexters.keyme.domain.question.exceptions.code.QuestionErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeNotFoundException;

public class NotFoundQuestionException extends KeymeNotFoundException {
    public NotFoundQuestionException() {
        super(QuestionErrorCode.NOT_FOUND_QUESTION.getMessage(), QuestionErrorCode.NOT_FOUND_QUESTION.getCode());
    }
}
