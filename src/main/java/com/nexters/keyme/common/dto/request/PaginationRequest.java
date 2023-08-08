package com.nexters.keyme.common.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "페이지네이션")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {

    @ApiModelProperty(value = "가져올 갯수", example = "10")
    private int limit = 10;

    @ApiModelProperty(value = "마지막 게시글의 Id", example = "123")
    private Long cursor;
}
