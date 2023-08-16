package com.nexters.keyme.notification.service;

import com.nexters.keyme.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.notification.dto.UserNotificationRequest;

import java.util.concurrent.CompletableFuture;

public interface NotificationService {
    CompletableFuture<Boolean> sendByUsers(UserNotificationRequest request);

    void sendByTopics(TopicNotificationRequest request);
}
