package com.nexters.keyme.domain.question.presentation.controller;

import com.nexters.keyme.domain.question.application.QuestionService;
import com.nexters.keyme.domain.question.dto.request.QuestionListScoreRequest;
import com.nexters.keyme.domain.question.dto.request.QuestionStatisticRequest;
import com.nexters.keyme.domain.question.dto.response.QuestionResponse;
import com.nexters.keyme.domain.question.dto.response.QuestionScoreInfoResponse;
import com.nexters.keyme.domain.question.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.global.common.annotation.RequestUser;
import com.nexters.keyme.global.common.dto.internal.UserInfo;
import com.nexters.keyme.global.common.dto.response.ApiResponse;
import com.nexters.keyme.global.common.dto.response.PageResponse;
import com.nexters.keyme.domain.question.dto.request.QuestionScoreListRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "질문", description = "Questino 관련 API")
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{questionId}")
    @ApiOperation(value = "질문 정보 가져오기")
    public ResponseEntity<ApiResponse<QuestionResponse>> questionDetail(@PathVariable("questionId") Long questionid) {
        QuestionResponse questionResponse = questionService.getQuestion(questionid);
        return ResponseEntity.ok(new ApiResponse<>(questionResponse));
    }

    @GetMapping("/{questionId}/result/scores")
    @ApiOperation(value = "해당 Question을 푼 사람들의 점수리스트 가져오기")
    public ResponseEntity<ApiResponse<PageResponse<QuestionScoreInfoResponse>>> getQuestionResultScoreList(
        @PathVariable("questionId") Long questionId,
        QuestionScoreListRequest request
    ) {
        PageResponse<QuestionScoreInfoResponse> questionSolvedPageResponse = questionService.getQuestionSolvedList(questionId, request);
        return ResponseEntity.ok(new ApiResponse<>(questionSolvedPageResponse));
    }

    @GetMapping("/{questionId}/result/statistics")
    @ApiOperation(value = "Question 통계 가져오기")
    public ResponseEntity<ApiResponse<QuestionStatisticResponse>> getQuestionStatistic(
        @RequestUser UserInfo requestUser,
        @PathVariable("questionId") Long questionId,
        QuestionStatisticRequest request
    ) {
        QuestionStatisticResponse questionStatisticResponse = questionService.getQuestionStatistic(requestUser.getMemberId(), questionId, request);
        return ResponseEntity.ok(new ApiResponse<>(questionStatisticResponse));
    }

    @GetMapping("/result/scores")
    @Deprecated
    @ApiOperation(value = "ids 기반으로 점수리스트 가져오기")
    public ResponseEntity<ApiResponse<List<QuestionScoreInfoResponse>>> getQuestionListScore(
        QuestionListScoreRequest request
    ) {
        List<QuestionScoreInfoResponse> questionScoreResponse = questionService.getQuestionSolvedScore(request);
        return ResponseEntity.ok(new ApiResponse<>(questionScoreResponse));
    }
}