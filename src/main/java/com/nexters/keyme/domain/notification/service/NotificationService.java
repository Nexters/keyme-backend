package com.nexters.keyme.domain.notification.service;

import com.nexters.keyme.domain.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.domain.notification.dto.UserNotificationRequest;

import java.util.concurrent.CompletableFuture;

public interface NotificationService {
    CompletableFuture<Boolean> sendByUser(UserNotificationRequest request);

    void sendByTopics(TopicNotificationRequest request);
}
