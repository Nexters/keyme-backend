package com.nexters.keyme.domain.member.presentation.handler;


import com.nexters.keyme.domain.member.domain.exceptions.NicknameVerificationException;
import com.nexters.keyme.global.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MemberStateHandler {
    @ExceptionHandler(NicknameVerificationException.class)
    public ResponseEntity<ApiResponse> handleNicknameVerificationException(NicknameVerificationException e) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(e.getCode(), e.getMessage(), null));
    }
}
