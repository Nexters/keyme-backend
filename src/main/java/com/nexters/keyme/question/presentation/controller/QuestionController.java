package com.nexters.keyme.question.presentation.controller;

import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "질문", description = "Questino 관련 API")
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    
    @GetMapping("/{id}")
    @ApiOperation(value = "질문 정보 가져오기")
    public ResponseEntity<ApiResponse<QuestionResponse>> questionDetail(@PathVariable("id") Long questionid) {
        return ResponseEntity
                .ok(new ApiResponse<QuestionResponse>(HttpStatus.OK, new QuestionResponse()));
    }

    @GetMapping("/{id}/solved-members")
    public ResponseEntity<ApiResponse> questionSolved(Long questionid, Long solverId) {
        return null;
    }
}