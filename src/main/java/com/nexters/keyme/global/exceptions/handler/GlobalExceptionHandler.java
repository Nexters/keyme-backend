package com.nexters.keyme.global.exceptions.handler;

import com.nexters.keyme.global.dto.response.ApiResponse;
import com.nexters.keyme.global.exceptions.*;
import com.nexters.keyme.global.exceptions.code.ErrorCode;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileUploadFailedException.class)
    public ResponseEntity<ApiResponse> handleFileUploadFailedException(FileUploadFailedException e) {
        log.warn("파일 업로드에 실패하였습니다.");
        return ResponseEntity.badRequest().body(ApiResponse.error("파일 업로드에 실패하였습니다. 파일 형식 및 크기를 확인해주세요", 400));
    }

    // @Valid 예외
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage(), 400));
    }

    @ExceptionHandler({
        MethodArgumentTypeMismatchException.class, // 요청 타입 불일치(바인딩 에러)
        HttpRequestMethodNotSupportedException.class // 지원하지 않는 HTTP Method
    })
    public ResponseEntity<ApiResponse> handleNotFulfilledException(Exception e) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(errorCode.getMessage(), errorCode.getCode()));
    }

    @ExceptionHandler(KeymeException.class)
    public ResponseEntity<ApiResponse> handleKeymeException(KeymeException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(ApiResponse.error(e.getMessage(), e.getCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("잘못된 인자를 넘기고 있습니다. {}", e.getCause());
        return ResponseEntity.internalServerError().body(ApiResponse.internalError());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleUnHandleException(Exception e) {
        log.error("예상치 못한 에러발생! 😭 {} {} {}", e.getCause(), e.getMessage(), Arrays.toString(e.getStackTrace()));
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;

        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(errorCode.getMessage(), errorCode.getCode()));
    }
}