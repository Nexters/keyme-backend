package com.nexters.keyme.member.presentation.handler;


import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.member.domain.exceptions.NicknameVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MemberStateHandler {
    @ExceptionHandler(NicknameVerificationException.class)
    public ResponseEntity<ApiResponse> handleNicknameVerificationException(NicknameVerificationException e) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(e.getState(), e.getMessage(), null));
    }
}
