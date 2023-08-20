package com.nexters.keyme.domain.clienttest.presentation.controller;

import com.nexters.keyme.global.dto.internal.UserInfo;
import com.nexters.keyme.domain.clienttest.application.ClientTestService;
import com.nexters.keyme.global.annotation.RequestUser;
import com.nexters.keyme.global.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nexters.keyme.global.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@Api(tags = "클라이언트 테스트 API", description = "테스트를 위한 초기화 API")
@RestController
@RequestMapping("/clienttest")
@RequiredArgsConstructor
public class ClientTestController {
    private final ClientTestService clientTestService;

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

    @DeleteMapping("/member")
    @ApiOperation(value = "멤버 정보 삭제")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse> deleteMember(@RequestUser UserInfo userInfo) {
        clientTestService.clearMember(userInfo.getMemberId());
        return ResponseEntity.ok(new ApiResponse(200, "멤버가 삭제되었습니다.", null));
    }
}
