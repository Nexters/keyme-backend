package com.nexters.keyme.notification.service;

import com.nexters.keyme.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.notification.dto.UserNotificationRequest;

public interface NotificationService {
    void sendByUsers(UserNotificationRequest request);

    void sendByTopics(TopicNotificationRequest request);
}
