package com.nexters.keyme.notification.service;

import com.nexters.keyme.member.domain.model.MemberDevice;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.notification.dto.UserNotificationRequest;
import com.nexters.keyme.notification.helper.NotificationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationSender notificationSender;
    private final MemberRepository memberRepository;

    @Async("NotificationThreadPool")
    public CompletableFuture<Boolean> sendByUsers(UserNotificationRequest request) {
        List<String> tokens = new ArrayList<>();

        for (MemberEntity member : memberRepository.findAllById(request.getUserIds())) {
            for (MemberDevice device : member.getMemberDevice()) {
                tokens.add(String.valueOf(device.getId()));
            }

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
