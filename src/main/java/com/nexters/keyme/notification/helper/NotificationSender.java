package com.nexters.keyme.notification.helper;

import java.util.List;

public interface NotificationSender {
    void sendByTokens(List<String> tokens, String title, String body);

    void sendByTopics(List<String> topics, String title, String body);
}
