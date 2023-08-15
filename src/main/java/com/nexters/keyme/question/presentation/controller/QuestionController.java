package com.nexters.keyme.question.presentation.controller;

import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.common.dto.response.PageResponse;
import com.nexters.keyme.question.application.QuestionService;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedListRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionStatisticRequest;
import com.nexters.keyme.question.presentation.dto.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "질문", description = "Questino 관련 API")
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{id}")
    @ApiOperation(value = "질문 정보 가져오기")
    public ResponseEntity<ApiResponse<QuestionResponse>> questionDetail(@PathVariable("id") Long questionid) {
        QuestionResponse questionResponse = questionService.getQuestion(questionid);
        return ResponseEntity.ok(new ApiResponse<>(questionResponse));
    }

    @GetMapping("/{id}/score")
    @ApiOperation(value = "Question 점수 가져오기")
    public ResponseEntity<ApiResponse<QuestionScoreResponse>> getQuestionSolvedScore(
        @PathVariable("id") Long questionId,
        QuestionSolvedRequest request
    ) {
        QuestionScoreResponse questionScoreResponse = questionService.getQuestionSolvedScore(questionId, request);
        return ResponseEntity.ok(new ApiResponse<>(questionScoreResponse));
    }

    @GetMapping("/{id}/statistics")
    @ApiOperation(value = "Question 통계 가져오기")
    public ResponseEntity<ApiResponse<QuestionStatisticResponse>> getQuestionStatistic(
        @PathVariable("id") Long questionId,
        QuestionStatisticRequest request
    ) {
        QuestionStatisticResponse questionStatisticResponse = questionService.getQuestionStatistic(questionId, request);
        return ResponseEntity.ok(new ApiResponse<>(questionStatisticResponse));
    }


    @GetMapping("/{id}/solved-scores")
    @ApiOperation(value = "Question 푼 사람 점수리스트 가져오기")
    public ResponseEntity<ApiResponse<PageResponse<QuestionSolvedScoreInfoResponse>>> getQuestionSolvedList(
        @PathVariable("id") Long questionId,
        QuestionSolvedListRequest request
    ) {
        PageResponse<QuestionSolvedScoreInfoResponse> questionSolvedPageResponse = questionService.getQuestionSolvedList(questionId, request);
        return ResponseEntity.ok(new ApiResponse<>(questionSolvedPageResponse));
    }
}