package com.nexters.keyme.member.domain.state;

public enum MemberStateCode {
    DUPLICATED_NICKNAME("이미 사용 중인 닉네임입니다.", 200),
    NICKNAME_TOO_LONG("닉네임은 7자 이하여야 합니다.", 200);

    private final String message;
    private final int code;

    MemberStateCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int code() { return this.code; }

    public String message() {
        return this.message;
    }
}
