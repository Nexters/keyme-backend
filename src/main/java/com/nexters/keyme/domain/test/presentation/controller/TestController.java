package com.nexters.keyme.domain.test.presentation.controller;

import com.nexters.keyme.global.common.dto.internal.UserInfo;
import com.nexters.keyme.domain.test.application.TestService;
import com.nexters.keyme.domain.test.dto.request.TestSubmissionRequest;
import com.nexters.keyme.domain.test.dto.response.SingleTestStatisticsResponse;
import com.nexters.keyme.domain.test.dto.response.TestDetailResponse;
import com.nexters.keyme.domain.test.dto.response.TestResultResponse;
import com.nexters.keyme.domain.test.dto.response.TestSubmitResponse;
import com.nexters.keyme.global.common.annotation.RequestUser;
import com.nexters.keyme.global.common.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "테스트", description = "테스트/결과 관련 API")
@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {
    final private TestService testService;

    @GetMapping("/onboarding")
    @ApiOperation(value = "내 온보딩 테스트 가져오기 (모바일 전용 API)")
    public ResponseEntity<ApiResponse<TestDetailResponse>> getOnboardingTest(@RequestUser UserInfo requestUser) {
        TestDetailResponse testDetailResponse = testService.getOnboardingTest(requestUser.getMemberId());
        return ResponseEntity.ok(new ApiResponse(testDetailResponse) );
    }

    @GetMapping("/daily")
    @ApiOperation(value = "내 데일리 테스트 가져오기")
    public ResponseEntity<ApiResponse<TestDetailResponse>> getDailyTest(@RequestUser UserInfo requestUser) {
        TestDetailResponse testDetailResponse = testService.getDailyTest(requestUser.getMemberId());
        return ResponseEntity.ok(new ApiResponse(testDetailResponse) );
    }

    @GetMapping("/{testId}")
    @ApiOperation(value = "testId 기반으로 테스트 가져오기")
    public ResponseEntity<ApiResponse<TestDetailResponse>> getTest(
        @RequestUser UserInfo requestUser,
        @PathVariable("testId") Long testId
    ) {
        TestDetailResponse testDetailResponse = testService.getSpecificTest(requestUser.getMemberId(), testId);
        return ResponseEntity.ok(new ApiResponse(testDetailResponse) );
    }

    @GetMapping("/{testId}/statistics")
    @ApiOperation(value = "해당 테스트의 통계정보 가져오기")
    public ResponseEntity<ApiResponse<SingleTestStatisticsResponse>> getTestStatistics(
        @RequestUser UserInfo requestUser,
        @PathVariable("testId") Long testId
    ) {
        SingleTestStatisticsResponse testStatisticsResponse = testService.getTestStatistics(requestUser.getMemberId(), testId);
        return ResponseEntity.ok(new ApiResponse(testStatisticsResponse) );
    }

    // Not MMVP
//    @GetMapping
//    @ApiOperation(value = "테스트 리스트 가져오기")
//    public ResponseEntity<ApiResponse<List<TestFeedResponse>>> getTestList(
//        @RequestUser UserInfo requestUser,
//        TestListRequest requestParameters
//    ) {
//        return null;
//    }

    @PostMapping("/{testId}/submit")
    @ApiOperation(value = "테스트 제출")
    public ResponseEntity<ApiResponse<TestSubmitResponse>> submitTest(
        @RequestUser UserInfo userInfo,
        @PathVariable("testId") Long testId,
        @RequestBody TestSubmissionRequest requestBody
    ) {
        TestSubmitResponse testSubmitResponse = testService.createTestResult(userInfo.getMemberId(), testId, requestBody);
        return ResponseEntity.ok(new ApiResponse(testSubmitResponse) );
    }

    @GetMapping("/result/{resultId}")
    @ApiOperation(value = "test resultId로 결과확인")
    public ResponseEntity<ApiResponse<TestResultResponse>> getResult(
        @RequestUser UserInfo userInfo,
        @PathVariable("resultId") Long resultId
    ) {
        TestResultResponse testResultResponse = testService.getTestResult(resultId);
        return ResponseEntity.ok(new ApiResponse(testResultResponse) );
    }
}
