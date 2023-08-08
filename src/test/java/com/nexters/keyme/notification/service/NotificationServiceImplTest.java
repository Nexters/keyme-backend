package com.nexters.keyme.notification.service;

import com.nexters.keyme.notification.dto.UserNotificationRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

class NotificationServiceImplTest {
    @Test
    void dtoBuilderTest() {
        UserNotificationRequest request = UserNotificationRequest.builder()
                .title("title")
                .body("body")
                .userIds(List.of(1L, 2L))
                .info("infoKey", "value")
                .build();
//
//        assertThat(request.getInfo("infoKey")).isPresent();
//        assertThat(request.getInfo("infoKey").get()).isEqualTo("value");
    }
}