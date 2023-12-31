package com.nexters.keyme.global.common.exceptions.handler;

import com.nexters.keyme.global.common.dto.response.ApiResponse;
import com.nexters.keyme.global.common.exceptions.FileUploadFailedException;
import com.nexters.keyme.global.common.exceptions.KeymeException;
import com.nexters.keyme.global.common.exceptions.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
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

    // 파일 업로드 실패
    @ExceptionHandler(FileUploadFailedException.class)
    public ResponseEntity<ApiResponse> handleFileUploadFailedException(FileUploadFailedException e) {
        log.warn("파일 업로드에 실패하였습니다.");
        return ResponseEntity.badRequest().body(ApiResponse.error("파일 업로드에 실패하였습니다. 파일 형식 및 크기를 확인해주세요", 400));
    }

    // 없는 API 호출
    @ExceptionHandler({
        MethodArgumentTypeMismatchException.class, // 요청 타입 불일치(바인딩 에러)
        HttpRequestMethodNotSupportedException.class // 지원하지 않는 HTTP Method
    })
    public ResponseEntity<ApiResponse> handleNotFulfilledException(Exception e) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(errorCode.getMessage(), errorCode.getCode()));
    }

    // @Valid 예외
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.ok().body(ApiResponse.error(e.getMessage(), 400));
    }

    @ExceptionHandler(KeymeException.class)
    public ResponseEntity<ApiResponse> handleKeymeException(KeymeException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getMessage(), e.getCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("서버 로직 중 잘못된 인자를 넘기고 있습니다. {}", e.getCause());
        ErrorCode errorCode = ErrorCode.SERVER_ERROR;

        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(errorCode.getMessage(), errorCode.getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleUnHandleException(Exception e) {
        log.error("예상치 못한 에러발생! 😭 {} {} {}", e.getCause(), e.getMessage(), Arrays.toString(e.getStackTrace()));
        ErrorCode errorCode = ErrorCode.SERVER_ERROR;

        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(errorCode.getMessage(), errorCode.getCode()));
    }
}