package com.nexters.keyme.domain.test.presentation.dto.response;

import com.nexters.keyme.domain.question.presentation.dto.response.QuestionResponse;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@ApiModel(value = "단일 Test 응답 객체")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDetailResponse {
    private Long testId;
    private Long testResultId;
    private int solvedCount;
    private String title;
    private TestSimpleMemberResponse owner;
    private List<QuestionResponse> questions;
}
