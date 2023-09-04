package com.nexters.keyme.global.common.event.handler;

import com.nexters.keyme.domain.notification.dto.ProblemSolvedNotificationRequest;
import com.nexters.keyme.domain.notification.application.NotificationService;
import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.statistics.application.StatisticService;
import com.nexters.keyme.domain.statistics.dto.internal.ScoreInfo;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.service.validator.TestResultValidator;
import com.nexters.keyme.global.common.event.message.AddStatisticEvent;
import com.nexters.keyme.global.common.event.message.SendQuestionSolvedNotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GlobalEventHandler {

    private final StatisticService statisticService;
    private final NotificationService notificationService;
    private final TestResultValidator testResultValidator;

    @EventListener
    public void handleAddStatisticEvent(AddStatisticEvent event) {
        TestResult testResult = testResultValidator.validateTestResult(event.getResultId());

        for (QuestionSolved questionSolved : testResult.getQuestionSolvedList()) {
            Question question = questionSolved.getQuestion();
            ScoreInfo info = new ScoreInfo(event.getTestOwnerId(), event.getSolverId(), question.getQuestionId(), questionSolved.getScore());
            statisticService.addNewScores(info);
        }

    }

    @EventListener
    public void handleSendNotificationEvent(SendQuestionSolvedNotificationEvent event) {
        Long solverId = event.getSolverId();
        Long ownerId = event.getOwnerId();

        ProblemSolvedNotificationRequest request = new ProblemSolvedNotificationRequest(ownerId, solverId);
        notificationService.sendQuestionSolvedNotification(request);
    }

}
