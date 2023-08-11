package com.nexters.keyme.test.presentation.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("테스트 등록 Body")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestRegistrationRequest {
    private String resultCode;
}
