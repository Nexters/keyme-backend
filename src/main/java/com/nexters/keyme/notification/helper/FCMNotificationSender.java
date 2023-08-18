package com.nexters.keyme.notification.helper;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class FCMNotificationSender implements NotificationSender {

    private final FirebaseMessaging firebaseMessaging;


    public void sendByTokens(List<String> tokens, String title, String body, Map<String, String> data) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        List<Message> messages = convertToMessages(tokens, data, notification);

        if (messages.size() == 0) {
            return;
        }

        for (String token : tokens) {
            log.info("Sending FCM Notification --- token: {}", token);
        }

        BatchResponse batchResponse = sendAndGetBatchResponse(messages);
        logError(batchResponse);
    }

    private List<Message> convertToMessages(List<String> tokens, Map<String, String> data, Notification notification) {
        return tokens.stream()
                .map(token ->
                        Message.builder()
                                .setNotification(notification)
                                .setToken(token)
                                .putAllData(data)
                                .build())
                .collect(Collectors.toList());
    }

    private BatchResponse sendAndGetBatchResponse(List<Message> messages) {
        BatchResponse batchResponse;
        try {

             batchResponse = firebaseMessaging.sendAll(messages);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("메시지 발송 중 문제가 발생했습니다.");
        }
        return batchResponse;
    }

    private void logError(BatchResponse batchResponse) {
        if (batchResponse.getFailureCount() == 0) {
            return;
        }

        for (SendResponse response : batchResponse.getResponses()) {
            if (response.getException() != null) {
                FirebaseMessagingException exception = response.getException();

                String message = exception.getMessage();
                String fcmErrorCode = exception.getErrorCode().name();
                log.error("Sending FCM notification failed --- {} --- {}\n", fcmErrorCode, message);
            }
        }
    }

    public void sendByTopics(List<String> topics, String title, String body, Map<String, String> data) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        List<Message> messages = topics.stream()
                .map(topic ->
                        Message.builder()
                                .setNotification(notification)
                                .setTopic(topic)
                                .putAllData(data)
                                .build())
                .collect(Collectors.toList());

        try {
            firebaseMessaging.sendAll(messages);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException("메시지 발송 중 문제가 발생했습니다.");
        }
    }
}
