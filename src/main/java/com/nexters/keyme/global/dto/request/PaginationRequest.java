package com.nexters.keyme.global.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "페이지네이션")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {

    @ApiModelProperty(value = "가져올 갯수", example = "10")
    private int limit = 10;

    @ApiModelProperty(value = "마지막 게시글의 Id(첫 요청시에는 없어도 됨)", example = "123")
    private long cursor;
}
