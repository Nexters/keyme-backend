package com.nexters.keyme.domain.member.domain.exceptions;

import com.nexters.keyme.domain.member.domain.exceptions.code.MemberErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeNotFoundException;

public class NotFoundMemberException extends KeymeNotFoundException {
    public NotFoundMemberException() {
        super(MemberErrorCode.NOT_FOUND_MEMBER.getMessage(), MemberErrorCode.NOT_FOUND_MEMBER.getCode());
    }
}