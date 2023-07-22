package com.nexters.keyme.notification.service;

import com.nexters.keyme.dummy.DummyTopic;
import com.nexters.keyme.dummy.DummyTopicRepository;
import com.nexters.keyme.dummy.DummyUser;
import com.nexters.keyme.dummy.DummyUserRepository;
import com.nexters.keyme.notification.dto.TopicNotificationRequest;
import com.nexters.keyme.notification.dto.UserNotificationRequest;
import com.nexters.keyme.notification.helper.NotificationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationSender notificationSender;
    private final DummyUserRepository userRepository;
    private final DummyTopicRepository topicRepository;

    @Async("NotificationThreadPool")
    public void sendByUsers(UserNotificationRequest request) {
        List<String> tokens = new ArrayList<>();

        for (DummyUser user : userRepository.findAllByUserId(request.getUserIds())) {
            tokens.addAll(user.getDeviceIds());
        }

        notificationSender.sendByTokens(tokens, request.getTitle(), request.getBody(), request.getData());
    }

    @Async("NotificationThreadPool")
    public void sendByTopics(TopicNotificationRequest request) {
        List<String> topics = topicRepository.findAllByTopicId(request.getTopicIds()).stream()
                .map(DummyTopic::getName)
                .collect(Collectors.toList());

        notificationSender.sendByTopics(topics, request.getTitle(), request.getBody(), request.getData());
    }

}
