package com.nexters.keyme.dummy;

import com.nexters.keyme.notification.dto.UserNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DummyService {
    private final ApplicationEventPublisher eventPublisher;

    public void doLogicAndPublishNotification() {
        // do logic..

        UserNotificationRequest userNotificationRequest = UserNotificationRequest.builder()
                .title("title")
                .body("body")
                .info("token", "dummytoken")
                .build();

        eventPublisher.publishEvent(userNotificationRequest);
    }
}
