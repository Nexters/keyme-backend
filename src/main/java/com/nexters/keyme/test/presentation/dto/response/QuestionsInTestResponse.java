package com.nexters.keyme.test.presentation.dto.response;

import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@ApiModel(value = "단일 Test 응답 객체")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionsInTestResponse {
    private Long testId;
    private Boolean isSolved = false;
    private List<QuestionResponse> questions;
}
