package com.nexters.keyme.test.events;

import com.nexters.keyme.notification.dto.UserNotificationRequest;
import com.nexters.keyme.notification.service.NotificationService;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.statistics.application.StatisticService;
import com.nexters.keyme.statistics.application.dto.ScoreInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
@Slf4j
public class TestEventHandler {

    private final StatisticService statisticService;
    private final NotificationService notificationService;

    @EventListener
    public void handleAddStatisticEvent(AddStatisticEvent event) {
        for (QuestionSolved question : event.getQuestionSolveds()) {
            ScoreInfo info = new ScoreInfo(event.getTestOwnerId(), event.getSolverId(), question.getQuestion().getQuestionId(), question.getScore());
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
