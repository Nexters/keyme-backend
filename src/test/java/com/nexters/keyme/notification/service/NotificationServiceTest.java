package com.nexters.keyme.notification.service;

import com.nexters.keyme.domain.notification.dto.ProblemSolvedNotificationRequest;
import com.nexters.keyme.domain.notification.application.NotificationService;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@ActiveProfiles("test")
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;
//    @Test
//    @DisplayName("문제 풀이 알림 발송 테스트")
    void sendByUsers() throws ExecutionException, InterruptedException {
        ProblemSolvedNotificationRequest request = ProblemSolvedNotificationRequest.builder()
                .solverId(1L)
                .ownerId(2L)
                .build();

        CompletableFuture<Boolean> response = notificationService.sendQuestionSolvedNotification(request);

        Assertions.assertThat(response.get()).isTrue();
    }
}