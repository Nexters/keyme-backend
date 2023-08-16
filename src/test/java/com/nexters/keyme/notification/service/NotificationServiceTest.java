package com.nexters.keyme.notification.service;

import com.nexters.keyme.notification.dto.UserNotificationRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@ActiveProfiles("test")
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;
//    @Test
//    @DisplayName("유저별 알림 발송 테스트")
    void sendByUsers() throws ExecutionException, InterruptedException {
        UserNotificationRequest request = UserNotificationRequest.builder()
                .title("title")
                .body("body")
                .userIds(List.of(1L))
                .info("infoKey", "value")
                .build();

        CompletableFuture<Boolean> response = notificationService.sendByUsers(request);

        Assertions.assertThat(response.get()).isTrue();
    }

//    @Test
//    @DisplayName("토큰 정보가 없는 유저 알림 발송 테스트")
    void sendByUsersWithNoToken() throws ExecutionException, InterruptedException {
        UserNotificationRequest request = UserNotificationRequest.builder()
                .title("title")
                .body("body")
                .userIds(List.of(10L))
                .info("infoKey", "value")
                .build();

        CompletableFuture<Boolean> response = notificationService.sendByUsers(request);

        Assertions.assertThat(response.get()).isTrue();
    }
}