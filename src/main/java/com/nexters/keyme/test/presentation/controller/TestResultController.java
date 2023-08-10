package com.nexters.keyme.test.presentation.controller;

import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.common.annotation.RequestUser;
import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.test.presentation.dto.request.TestRegistRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nexters.keyme.common.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@Api(tags = "테스트 결과", description = "테스트 결과 관련 API")
@RestController
@RequestMapping("/tests/result")
@RequiredArgsConstructor
public class TestResultController {

    @PostMapping("/register")
    @ApiOperation(value = "익명 유저로 푼 결과 유저에 귀속")
    public ResponseEntity<ApiResponse> mappingResultToUser(
        @RequestUser UserInfo userInfo,
        @RequestBody TestRegistRequest requestBody
    ) {
        return null;
    }
}
