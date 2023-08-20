package com.nexters.keyme.domain.notification.handler;

import com.nexters.keyme.domain.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.domain.notification.dto.UserNotificationRequest;
import com.nexters.keyme.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationRequestHandler {

    private final NotificationService notificationService;

    @EventListener
    public void handleUserNotificationRequest(UserNotificationRequest request) {
        notificationService.sendByUser(request);
    }

    @EventListener
    public void handleTopicNotificationRequest(TopicNotificationRequest request) {
        notificationService.sendByTopics(request);
    }
}
