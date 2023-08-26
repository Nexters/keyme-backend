package com.nexters.keyme.domain.test.exceptions.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestErrorCode {

    NOT_FOUND_TEST(404, "Test를 찾을 수 없습니다."),
    NOT_FOUND_TEST_RESULT(404, "Test Result를 찾을 수 없습니다."),
    NOT_FOUND_TEST_RESULT_CODE(404, "Result Code가 존재하지 않습니다"),
    INVALID_DAILY_TEST(400, "Daily Test를 호출할 수 없습니다. 온보딩을 먼저 풀어주세요"),
    INVALID_TEST_RESULT_SUBMIT(400, "제출 정보가 잘못되었습니다."),
    ALREADY_EXIST_TEST_RESULT(400, "해당 유저의 Test Result가 이미 존재합니다."),
    ALREADY_EXIST_TEST_RESULT_OWNER(400, "해당 Test Result는 다른 유저의 Result입니다.");

    private final int code;
    private final String message;
}
