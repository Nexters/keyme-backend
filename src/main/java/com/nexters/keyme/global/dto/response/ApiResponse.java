package com.nexters.keyme.global.dto.response;

import com.nexters.keyme.global.exceptions.code.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    @ApiModelProperty(value="코드", example = "200")
    private int code;
    @ApiModelProperty(value="메시지", example = "SUCCESS")
    private String message;
    private T data;

    static public ApiResponse success(Object data) {
        return new ApiResponse(200, "요청에 성공했습니다.", data);
    }

    static public ApiResponse emptySuccess() {
        return new ApiResponse(200, "요청에 성공했습니다.", null);
    }

    static public ApiResponse error(String message, int code) {
        return new ApiResponse(code, message, null);
    }

    static public ApiResponse internalError() {
        return new ApiResponse(500, "문제가 발생했습니다. 문의주세요!", null);
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
