package com.nexters.keyme.test.presentation.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "테스트 제출 Body")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TestSubmissionRequest {

    private Boolean isOnboarding = false;
    private List<QuestionSubmission> results;

    @Getter
    static public class QuestionSubmission {
        private Long questionId;
        private int score;
    }
}
