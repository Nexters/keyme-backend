package com.nexters.keyme.global.common.exceptions.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    TOKEN_EXPIRED(401, "액세스 토큰이 만료되었습니다."),
    ACCESS_TOKEN_NOT_EXIST(401, "액세스 토큰이 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_EXIST(401, "리프레시 토큰이 존재하지 않습니다."),
    TOKEN_INVALID(401, "올바르지 않은 토큰입니다."),
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다."),

    INVALID_REQUEST(400, "요청 형식이 잘못되었습니다. 다시 확인해주세요"),

    SERVER_ERROR(500, "서버 에러 발생! 문의주세요.");

    private final int code;
    private final String message;
}
