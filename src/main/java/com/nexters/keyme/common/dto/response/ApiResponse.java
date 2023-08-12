package com.nexters.keyme.common.dto.response;

import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    @ApiModelProperty(value="HTTP 상태코드", example = "200")
    private String state;
    @ApiModelProperty(value="메시지", example = "SUCCESS")
    private String message;
    private T data;

    static public ApiResponse emptySuccess() {
        return new ApiResponse("SUCCESS", "요청에 성공했습니다.", null);
    }

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

    public ApiResponse(ErrorCode errorCode) {
        this.state = errorCode.name();
        this.message = errorCode.message();
        this.data = null;
    }

}
