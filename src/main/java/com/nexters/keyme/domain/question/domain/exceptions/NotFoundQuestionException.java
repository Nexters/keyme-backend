package com.nexters.keyme.domain.question.domain.exceptions;

import com.nexters.keyme.domain.question.domain.exceptions.code.QuestionErrorCode;
import com.nexters.keyme.global.exceptions.KeymeNotFoundException;

public class NotFoundQuestionException extends KeymeNotFoundException {
    public NotFoundQuestionException() {
        super(QuestionErrorCode.NOT_FOUND_QUESTION.getMessage(), QuestionErrorCode.NOT_FOUND_QUESTION.getCode());
    }
}
