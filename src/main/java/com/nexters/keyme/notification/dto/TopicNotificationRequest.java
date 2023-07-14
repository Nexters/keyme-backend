package com.nexters.keyme.notification.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class TopicNotificationRequest {
    @Getter
    private List<Long> topicIds;
    @Getter
    private final String title;
    @Getter
    private final String body;
    private final Map<String, String> info;
}
