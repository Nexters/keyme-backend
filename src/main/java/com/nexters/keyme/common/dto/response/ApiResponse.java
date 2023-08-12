package com.nexters.keyme.common.dto.response;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    @ApiModelProperty(value="코드", example = "200")
    private int code;
    @ApiModelProperty(value="메시지", example = "SUCCESS")
    private String message;
    private T data;

    static public ApiResponse emptySuccess() {
        return new ApiResponse(200, "요청에 성공했습니다.", null);
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(T data) {
        this.code = 200;
        this.message = "요청에 성공했습니다.";
        this.data = data;
    }

    public ApiResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = null;
    }
}
