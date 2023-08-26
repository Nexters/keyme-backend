package com.nexters.keyme.domain.test.dto.response;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Test 객체 내 Rate 정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestResultRateResponse {
    private float averageRate;
    private float matchRate;
}
