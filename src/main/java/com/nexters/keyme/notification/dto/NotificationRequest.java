package com.nexters.keyme.notification.dto;

import java.util.HashMap;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String body;
        private Map<String, String> info = new HashMap<>();

        private Builder() {}

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder info(String key, String value) {
            this.info.put(key, value);
            return this;
        }

        public NotificationRequest build() {
            return new NotificationRequest(this.title, this.body, this.info);
        }
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
