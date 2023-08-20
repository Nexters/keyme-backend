package com.nexters.keyme.domain.member.domain.exceptions;

import com.nexters.keyme.domain.member.domain.state.MemberStateCode;

public class NicknameVerificationException extends RuntimeException {

    private MemberStateCode code;

    public NicknameVerificationException(MemberStateCode stateCode) {
        this.code = stateCode;
    }

    public int getCode() { return this.code.code(); }
    public String getMessage() { return this.code.message(); }
}
