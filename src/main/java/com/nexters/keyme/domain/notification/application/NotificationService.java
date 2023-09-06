package com.nexters.keyme.domain.notification.application;

import com.nexters.keyme.domain.notification.dto.ProblemSolvedNotificationRequest;

import java.util.concurrent.CompletableFuture;

public interface NotificationService {
    CompletableFuture<Boolean> sendQuestionSolvedNotification(ProblemSolvedNotificationRequest request);

}
