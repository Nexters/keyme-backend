package com.nexters.keyme.common.exceptions.code;

public enum ErrorCode {
    INVALID_REQUEST(400, "올바르지 않은 요청입니다. 요청 형식 등을 확인하세요."),

    TOKEN_EXPIRED(401, "액세스 토큰이 만료되었습니다."),
    ACCESS_TOKEN_NOT_EXIST(401, "액세스 토큰이 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_EXIST(401, "리프레시 토큰이 존재하지 않습니다."),
    TOKEN_INVALID(401, "올바르지 않은 토큰입니다."),
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다."),

    ACCESS_DENIED(403, "접근 권한이 없습니다."),

    RESOURCE_NOT_FOUND(404, "대상을 찾을 수 없습니다."),
    FILE_UPLOAD_FAILED(500, "파일 업로드에 실패하였습니다. 재시도해 주세요."),

    RESOURCE_ALREADY_EXIST(409, "리소스가 이미 존재합니다."),

    SERVER_ERROR(500, "서버 에러 발생! 문의주세요.");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
