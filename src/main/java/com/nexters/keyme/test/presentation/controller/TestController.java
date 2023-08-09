package com.nexters.keyme.test.presentation.controller;

import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.common.annotation.RequestUser;
import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.test.application.TestService;
import com.nexters.keyme.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.test.presentation.dto.request.TestResultRequest;
import com.nexters.keyme.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.test.presentation.dto.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nexters.keyme.common.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@Api(tags = "테스트", description = "테스트/결과 관련 API")
@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {
    final private TestService testService;

    @GetMapping("/onboarding")
    @ApiOperation(value = "내 온보딩 테스트 가져오기 (모바일 전용 API)")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<TestDetailResponse>> getOnboardingTest(@RequestUser UserInfo requestUser) {
        TestDetailResponse testDetailResponse = testService.getOrCreateOnboardingTest(requestUser.getMemberId());
        return ResponseEntity.ok(new ApiResponse(testDetailResponse) );
    }

    @GetMapping("/daily")
    @ApiOperation(value = "내 데일리 테스트 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<TestDetailResponse>> getDailyTest(@RequestUser UserInfo requestUser) {
        TestDetailResponse testDetailResponse = testService.getOrCreateDailyTest(requestUser.getMemberId());
        return ResponseEntity.ok(new ApiResponse(testDetailResponse) );
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "testId 기반으로 테스트 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<TestDetailResponse>> getTest(
        @RequestUser UserInfo requestUser,
        @PathVariable("id") Long testId
    ) {
        TestDetailResponse testDetailResponse = testService.getSpecificTest(requestUser.getMemberId(), testId);
        return ResponseEntity.ok(new ApiResponse(testDetailResponse) );
    }

    @GetMapping("/{id}/statistics")
    @ApiOperation(value = "해당 테스트의 통계정보 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<SingleTestStatisticsResponse>> getTestStatistics(
        @RequestUser UserInfo requestUser,
        @PathVariable("id") Long testId
    ) {
        SingleTestStatisticsResponse testStatisticsResponse = testService.getTestStatistics(requestUser.getMemberId(), testId);
        return ResponseEntity.ok(new ApiResponse(testStatisticsResponse) );
    }

    // Not MMVP
    @GetMapping
    @ApiOperation(value = "테스트 리스트 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<List<TestFeedResponse>>> getTestList(
        @RequestUser UserInfo requestUser,
        TestListRequest requestParameters
    ) {
        return null;
    }

    @PostMapping("/{id}/submit")
    @ApiOperation(value = "테스트 제출")
    public ResponseEntity<ApiResponse<TestSubmitResponse>> submitTest(
        @RequestUser UserInfo userInfo,
        @PathVariable("id") Long testId,
        @RequestBody TestSubmissionRequest requestBody
    ) {
        TestSubmitResponse testSubmitResponse = testService.createTestResult(userInfo.getMemberId(), testId, requestBody);
        return ResponseEntity.ok(new ApiResponse(testSubmitResponse) );
    }

    @GetMapping("/{id}/result/{resultId}")
    @ApiOperation(value = "해당 테스트의 결과 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<TestResultResponse>> getResult(
        @RequestUser UserInfo userInfo,
        @PathVariable("id") Long testId,
        @PathVariable("resultId") Long resultId
    ) {
        TestResultResponse testResultResponse = testService.getTestResult(testId, resultId);
        return ResponseEntity.ok(new ApiResponse(testResultResponse) );
    }

    @GetMapping("/{id}/solved-members")
    @ApiOperation(value = "해당 테스트를 푼 유저리스트")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public void getSolvedMembers(
        @PathVariable("id") Long testId
    ) { }
}
