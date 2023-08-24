package com.nexters.keyme.domain.member.domain.exceptions;

import com.nexters.keyme.domain.member.domain.exceptions.code.MemberErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeSuccessInfoException;

public class NicknameDuplicateException extends KeymeSuccessInfoException {
    public NicknameDuplicateException() {
        super(MemberErrorCode.DUPLICATED_NICKNAME.getMessage(), MemberErrorCode.DUPLICATED_NICKNAME.getCode());
    }
}
