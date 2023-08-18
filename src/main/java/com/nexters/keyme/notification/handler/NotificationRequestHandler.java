package com.nexters.keyme.notification.handler;

import com.nexters.keyme.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.notification.dto.UserNotificationRequest;
import com.nexters.keyme.notification.service.NotificationService;
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
