package com.nexters.keyme.notification.dto;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
public class TopicNotificationRequest {
    @Getter
    private final List<Long> topicIds;
    @Getter
    private final String title;
    @Getter
    private final String body;
    @Singular("info")
    private final Map<String, String> info;

    public Optional<String> getInfo(String key) {
        return Optional.of(info.get(key));
    }
}
