package com.nexters.keyme.infra.interfaces;

import java.util.List;
import java.util.Map;

public interface NotificationSender {
    void sendByTokens(List<String> tokens, String title, String body, Map<String, String> data);

    void sendByTopics(List<String> topics, String title, String body, Map<String, String> data);
}
