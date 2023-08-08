package com.nexters.keyme.member.domain.state;

public enum MemberStateCode {
    DUPLICATED_NICKNAME("이미 사용 중인 닉네임입니다."),
    NICKNAME_TOO_LONG("닉네임은 7자 이하여야 합니다.");

    private final String message;

    MemberStateCode(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
