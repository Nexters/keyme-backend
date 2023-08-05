package com.nexters.keyme.test.presentation.controller;

import com.nexters.keyme.auth.dto.UserInfo;
import com.nexters.keyme.auth.resolver.RequestUser;
import com.nexters.keyme.common.dto.ApiResponse;
import com.nexters.keyme.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.test.presentation.dto.request.TestResultRequest;
import com.nexters.keyme.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.test.presentation.dto.response.QuestionsInTestResponse;
import com.nexters.keyme.test.presentation.dto.response.TestFeedResponse;
import com.nexters.keyme.test.presentation.dto.response.TestResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nexters.keyme.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@Api(tags = "테스트", description = "테스트/결과 관련 API")
@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/daily")
    @ApiOperation(value = "오늘의 테스트 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<QuestionsInTestResponse>> getDailyTest(@RequestUser UserInfo requestUser) {
        return null;
    }

    @GetMapping("/onboarding")
    @ApiOperation(value = "온보딩 테스트 가져오기")
    public ResponseEntity<ApiResponse<QuestionsInTestResponse>> getOnboardingTest() {
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "testId 기반으로 테스트 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<QuestionsInTestResponse>> getTest(
        @RequestUser UserInfo requestUser,
        @PathVariable("id") Long testId
    ) {
        return null;
    }

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
    public ResponseEntity<ApiResponse<TestResultResponse>> submitTest(
        @RequestUser UserInfo requestUser,
        @PathVariable("id") Long testId,
        @RequestBody TestSubmissionRequest requestBody
    ) {
        return null;
    }

    @GetMapping("/{id}/result")
    @ApiOperation(value = "해당 테스트의 결과 가져오기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<TestResultResponse>> getResult(
        @RequestUser UserInfo userInfo,
        @PathVariable("id") Long testId,
        TestResultRequest requestParameters
    ) {
        return null;
    }

    @GetMapping("/{id}/solved-members")
    @ApiOperation(value = "해당 테스트를 푼 유저리스트")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public void getSolvedMembers(
        @PathVariable("id") Long testId
    ) { }
}