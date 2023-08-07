package com.nexters.keyme.clienttest.presentation.controller;

import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.common.annotation.RequestUser;
import com.nexters.keyme.common.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nexters.keyme.common.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@Api(tags = "클라이언트 테스트 API", description = "테스트를 위한 초기화 API")
@RestController
@RequestMapping("/clienttest")
@RequiredArgsConstructor
public class ClientTestController {

    @GetMapping
    public void hi() { }

    @DeleteMapping("/tests/onboarding")
    @ApiOperation(value = "제출한 onboarding 삭제")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse> deleteOnboardingTest(@RequestUser UserInfo requestUser) {
        // 발급받은 온보딩 - test 삭제(test result도 삭
        return null;
    }

    @DeleteMapping("/tests/daily")
    @ApiOperation(value = "오늘 발급받은 테스트 삭제")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse> deleteDailyTest(@RequestUser UserInfo requestUser) {
        // 제출한 테스트도 삭제
        return null;
    }

    @PostMapping("/questions")
    @ApiOperation(value = "백엔드용")
    public ResponseEntity<ApiResponse> addMockQuesetion() {
        return null;
    }
}
