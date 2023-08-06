package com.nexters.keyme.notification.presentation.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class NotificationResponse {
    private final Long notificationId;
    private final String content;
    private final String type;
    private final Long resourceId;
    private final LocalDateTime createdAt;
    private final boolean isRead;
}
