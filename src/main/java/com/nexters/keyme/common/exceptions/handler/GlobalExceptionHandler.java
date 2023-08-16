package com.nexters.keyme.common.exceptions.handler;

import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.common.exceptions.*;
import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("접근이 허용되지 않은 리소스에 접근");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(ErrorCode.ACCESS_DENIED));
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ApiResponse> handleAuthorizationFailedException(AuthenticationFailedException e) {
        log.error("인증 실패");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(ErrorCode.UNAUTHORIZED.getCode(), e.getMessage(), null));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiResponse> handleInvalidRequestException(InvalidRequestException e) {
        log.error("잘못된 요청");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ErrorCode.INVALID_REQUEST));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("해당 리소스가 존재하지 않음");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(ErrorCode.RESOURCE_NOT_FOUND));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        log.error("해당 리소스가 이미 존재함");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(ErrorCode.RESOURCE_ALREADY_EXIST));
	}

    // 잘못된 형식의 요청
    @ExceptionHandler({
        IllegalArgumentException.class,
        MethodArgumentTypeMismatchException.class,
        HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<ApiResponse> handleNotFulfilledException(Exception e) {
        log.error("요청 형식이 잘못되었음");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(ErrorCode.INVALID_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleUnHandleException(Exception e) {
        log.error("예상치 못한 에러발생! 😭");
        return ResponseEntity.internalServerError().body(new ApiResponse(ErrorCode.SERVER_ERROR));
    }

}
