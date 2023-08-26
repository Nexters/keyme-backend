package com.nexters.keyme.domain.statistics.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AdditionalStatisticRequest {
    @ApiModelProperty(value = "가져올 갯수", example = "10")
    private int limit = 10;

    @ApiModelProperty(value = "마지막으로 받은 statisticId(첫 요청 시에는 없어도 됨)", example = "123")
    private long cursor;

    public AdditionalStatisticRequest(int limit, Long cursor) {
        this.limit = limit;
        this.cursor = cursor;
    }
}
