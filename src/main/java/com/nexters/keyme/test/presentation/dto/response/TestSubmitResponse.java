package com.nexters.keyme.test.presentation.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Test 제출 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSubmitResponse {
    private Long testResultId;

    @ApiModelProperty(value = "테스트 제출자와의 일치율(MMVP에서는 없음)", example = "87.7")
    private float matchRate;
}
