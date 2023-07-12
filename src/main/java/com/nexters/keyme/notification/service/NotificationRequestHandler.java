package com.nexters.keyme.notification.service;

import com.nexters.keyme.notification.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Async
@Component
public class NotificationRequestHandler {

    private final NotificationService fcmNotificationService;

    @EventListener
    public void handleNotificationRequest(NotificationRequest request) {
        fcmNotificationService.sendNotification(request);
    }
}
