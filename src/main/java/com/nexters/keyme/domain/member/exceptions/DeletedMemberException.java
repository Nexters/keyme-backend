package com.nexters.keyme.domain.member.exceptions;

import com.nexters.keyme.domain.member.exceptions.code.MemberErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeException;
import org.springframework.http.HttpStatus;

public class DeletedMemberException extends KeymeException {
    public DeletedMemberException(HttpStatus httpStatus, String message, int code) {
        super(HttpStatus.OK, MemberErrorCode.DELETED_MEMBER.getMessage(), MemberErrorCode.DELETED_MEMBER.getCode());
    }
}
