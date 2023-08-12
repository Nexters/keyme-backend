package com.nexters.keyme.member.domain.exceptions;

import com.nexters.keyme.member.domain.state.MemberStateCode;
import lombok.Getter;

public class NicknameVerificationException extends RuntimeException {

    private MemberStateCode code;

    public NicknameVerificationException(MemberStateCode stateCode) {
        this.code = stateCode;
    }

    public int getCode() { return this.code.code(); }
    public String getMessage() { return this.code.message(); }
}
