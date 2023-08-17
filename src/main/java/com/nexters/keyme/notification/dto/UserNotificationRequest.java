package com.nexters.keyme.notification.dto;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@RequiredArgsConstructor
public class UserNotificationRequest {
    private final List<Long> userIds;
    private final String title;
    private final String body;
    @Singular("info")
    @Getter(AccessLevel.NONE)
    private final Map<String, String> data;

    public Map<String, String> getData() {
        return data == null ? new HashMap<>() : new HashMap<>(data);
    }
}
