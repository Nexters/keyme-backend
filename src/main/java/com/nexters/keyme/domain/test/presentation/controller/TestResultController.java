package com.nexters.keyme.domain.test.presentation.controller;

import com.nexters.keyme.global.common.dto.internal.UserInfo;
import com.nexters.keyme.domain.test.application.TestResultService;
import com.nexters.keyme.domain.test.dto.request.TestRegistrationRequest;
import com.nexters.keyme.global.common.annotation.RequestUser;
import com.nexters.keyme.global.common.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "테스트 결과", description = "테스트 결과 관련 API")
@RestController
@RequestMapping("/tests/result")
@RequiredArgsConstructor
public class TestResultController {

    private final TestResultService testResultService;

    @PostMapping("/register")
    @ApiOperation(value = "익명 유저로 푼 결과 유저에 귀속")
    public ResponseEntity<ApiResponse> mappingResultToUser(
        @RequestUser UserInfo userInfo,
        @RequestBody TestRegistrationRequest requestBody
    ) {
        testResultService.injectTestResultToUser(userInfo.getMemberId(), requestBody.getResultCode());
        return ResponseEntity.ok(ApiResponse.emptySuccess());
    }
}
