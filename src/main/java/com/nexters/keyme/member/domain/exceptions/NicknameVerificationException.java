package com.nexters.keyme.member.domain.exceptions;

import com.nexters.keyme.member.domain.state.MemberStateCode;
import lombok.Getter;

@Getter
public class NicknameVerificationException extends RuntimeException {
    private String state;

    public NicknameVerificationException(MemberStateCode stateCode) {
        super(stateCode.message());
        this.state = stateCode.name();
    }
}
