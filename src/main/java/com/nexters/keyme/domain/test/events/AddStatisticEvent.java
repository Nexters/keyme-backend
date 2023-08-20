package com.nexters.keyme.domain.test.events;

import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import lombok.Getter;

import java.util.List;

@Getter
public class AddStatisticEvent {
    private Long testOwnerId;
    private Long solverId;
    private List<QuestionSolved> questionSolveds; // TODO: DTO 변환

    public AddStatisticEvent(Long testOwnerId, Long solverId, List<QuestionSolved> questionSolveds) {
        this.testOwnerId = testOwnerId;
        this.solverId = solverId;
        this.questionSolveds = questionSolveds;
    }
}
