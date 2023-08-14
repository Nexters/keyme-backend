package com.nexters.keyme.statistics.presentation.dto;

import com.nexters.keyme.common.dto.request.PaginationRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AdditionalStatisticRequest {
    @ApiModelProperty(value = "가져올 갯수", example = "10")
    private int limit = 10;

    @ApiModelProperty(value = "마지막 게시글의 Id(첫 요청시에는 없어도 됨)", example = "123")
    private Long cursor;

    public AdditionalStatisticRequest(int limit, Long cursor) {
        this.limit = limit;
        this.cursor = cursor;
    }
}
