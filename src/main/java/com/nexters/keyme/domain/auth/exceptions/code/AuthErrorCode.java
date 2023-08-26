package com.nexters.keyme.domain.auth.exceptions.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode {
    INVALID_APPLE_TOKEN(400, "잘못된 IdentityToken 입니다."),
    INVALID_APPLE_KEY(400, "IdentityToken 검증에 실패하였습니다.");

    private final int code;
    private final String message;
}
