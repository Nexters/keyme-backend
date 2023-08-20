package com.nexters.keyme.domain.notification.service;

import com.nexters.keyme.domain.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.domain.notification.dto.UserNotificationRequest;
import com.nexters.keyme.domain.member.domain.model.MemberDevice;
import com.nexters.keyme.domain.member.domain.repository.MemberDeviceRepository;
import com.nexters.keyme.domain.notification.helper.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationSender notificationSender;
    private final MemberDeviceRepository memberDeviceRepository;

    @Async
    public CompletableFuture<Boolean> sendByUser(UserNotificationRequest request) {
        List<String> tokens = new ArrayList<>();
        List<MemberDevice> devices = memberDeviceRepository.findAllByMemberId(request.getUserId());

        for (MemberDevice device : devices) {
            tokens.add(String.valueOf(device.getToken()));
        }

        notificationSender.sendByTokens(tokens, request.getTitle(), request.getBody(), request.getData());
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async("NotificationThreadPool")
    public void sendByTopics(TopicNotificationRequest request) {
        List<String> topics = List.of("topic1", "topic2");

        notificationSender.sendByTopics(topics, request.getTitle(), request.getBody(), request.getData());
    }

}
