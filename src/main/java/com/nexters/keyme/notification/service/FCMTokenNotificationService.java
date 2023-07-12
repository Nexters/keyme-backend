package com.nexters.keyme.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.nexters.keyme.notification.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMTokenNotificationService implements NotificationService {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public void sendNotification(NotificationRequest request) {
        String token = request.getInfo("token")
                .orElseThrow(() -> new RuntimeException("FCM 토큰이 올바르지 않습니다."));

        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException("메시지 발송 중 문제가 발생했습니다.");
        }

    }
}
