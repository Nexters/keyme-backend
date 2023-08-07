package com.nexters.keyme.common.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    @ApiModelProperty(value="HTTP 상태코드", example = "200")
    private String state;
    @ApiModelProperty(value="메시지", example = "SUCCESS")
    private String message;
    private T data;


    public ApiResponse(String status, String message, T data) {
        this.state = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(T data) {
        this.state = "SUCCESS";
        this.message = "요청에 성공했습니다.";
        this.data = data;
    }

}
