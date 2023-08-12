package com.nexters.keyme.test.events;

import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.statistics.application.StatisticService;
import com.nexters.keyme.statistics.application.dto.ScoreInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TestEventHandler {

    private final StatisticService statisticService;

    @EventListener
    public void handleAddStatisticEvent(AddStatisticEvent event) {
        for (QuestionSolved question : event.getQuestionSolveds()) {
            ScoreInfo info = new ScoreInfo(event.getTestOwnerId(), event.getSolverId(), question.getQuestion().getQuestionId(), question.getScore());
            statisticService.addNewScores(info);
        }

    }

}
