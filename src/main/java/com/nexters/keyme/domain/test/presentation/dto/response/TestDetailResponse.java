package com.nexters.keyme.domain.test.presentation.dto.response;

import com.nexters.keyme.domain.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.domain.test.domain.internaldto.TestDetailInfo;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value = "단일 Test 응답 객체")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDetailResponse {
    private Long testId;
    private Long testResultId;
    private String title;
    private TestSimpleMemberResponse owner;
    private List<QuestionResponse> questions;

    public TestDetailResponse(TestDetailInfo testDetailInfo, Long testResultId) {
        this.testId = testDetailInfo.getTestId();
        this.testResultId = testResultId;
        this.title = testDetailInfo.getTitle();
        this.owner = new TestSimpleMemberResponse(testDetailInfo.getOwner());
        this.questions = testDetailInfo.getQuestions().stream()
                .map(q -> new QuestionResponse(q))
                .collect(Collectors.toList());
    }
}
