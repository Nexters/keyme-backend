package com.nexters.keyme.domain.test.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "테스트 결과 요청 파라미터")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TestResultRequest {
    private Long solverId;
}