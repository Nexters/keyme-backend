package com.nexters.keyme.domain.member.domain.exceptions.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {
    NOT_FOUND_MEMBER(404, "Member를 찾을 수 없습니다."),
    DUPLICATED_NICKNAME(200,"이미 사용 중인 닉네임입니다."),
    NICKNAME_TOO_LONG(200,"닉네임은 7자 이하여야 합니다.");

    private final int code;
    private final String message;
}
