package com.nexters.keyme.notification.service;

import com.nexters.keyme.notification.dto.NotificationRequest;

public interface NotificationService {
    void sendNotification(NotificationRequest request);
}
