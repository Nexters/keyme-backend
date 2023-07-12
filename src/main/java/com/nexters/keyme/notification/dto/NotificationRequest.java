package com.nexters.keyme.notification.dto;

import java.util.Map;
import java.util.Optional;

public class NotificationRequest {
    private final String title;
    private final String body;
    private final Map<String, String> info;

    public NotificationRequest(String title, String body, Map<String, String> info) {
        this.title = title;
        this.body = body;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    
    public Optional<String> getInfo(String key) {
        return Optional.of(info.get(key));
    }
}
