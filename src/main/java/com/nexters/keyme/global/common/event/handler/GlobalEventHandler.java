package com.nexters.keyme.global.common.event.handler;

import com.nexters.keyme.domain.notification.dto.UserNotificationRequest;
import com.nexters.keyme.domain.notification.service.NotificationService;
import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.statistics.application.StatisticService;
import com.nexters.keyme.domain.statistics.dto.internal.ScoreInfo;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.service.validator.TestResultValidator;
import com.nexters.keyme.global.common.event.message.AddStatisticEvent;
import com.nexters.keyme.global.common.event.message.SendNotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
    public void handleSendNotificationEvent(SendNotificationEvent event) {
        Long solverId = event.getSolverId();
        Long ownerId = event.getOwnerId();

        if (Objects.equals(ownerId, solverId)) {
            return;
        }

        UserNotificationRequest request = new UserNotificationRequest(event.getOwnerId(), "내 문제를 푼 친구가 있어요!", "지금 Keyme에서 확인해보세요.", null);
        notificationService.sendByUser(request);
    }

}
