package com.nexters.keyme.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    @ApiModelProperty(value="HTTP 상태코드", example = "200")
    private int statusCode;
    @ApiModelProperty(value="메시지", example = "SUCCESS")
    private String message;
    private T data;

    public ApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus status, String message, T data) {
        this.statusCode = status.value();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus status, T data) {
        this.statusCode = status.value();
        this.message = status.getReasonPhrase();
        this.data = data;
    }
}
