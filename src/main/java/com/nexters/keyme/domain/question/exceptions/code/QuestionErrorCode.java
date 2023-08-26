package com.nexters.keyme.domain.question.exceptions.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionErrorCode {

    NOT_FOUND_QUESTION(400, "Question을 찾을 수 없습니다.");

    private final int code;
    private final String message;
}
