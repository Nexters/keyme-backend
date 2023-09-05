package com.nexters.keyme.domain.notification.application;

import com.nexters.keyme.domain.member.domain.model.MemberDevice;
import com.nexters.keyme.domain.member.domain.repository.MemberDeviceRepository;
import com.nexters.keyme.domain.notification.dto.ProblemSolvedNotificationRequest;
import com.nexters.keyme.infra.interfaces.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationSender notificationSender;
    private final MemberDeviceRepository memberDeviceRepository;

    public CompletableFuture<Boolean> sendQuestionSolvedNotification(ProblemSolvedNotificationRequest request) {
        Long ownerId = request.getOwnerId();
        Long solverId = request.getSolverId();

        if (Objects.equals(ownerId, solverId)) {
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }

        List<String> tokens = new ArrayList<>();
        List<MemberDevice> devices = memberDeviceRepository.findAllByMemberId(ownerId);

        for (MemberDevice device : devices) {
            tokens.add(String.valueOf(device.getToken()));
        }

        notificationSender.sendByTokens(tokens, "내 문제를 푼 친구가 있어요!", "지금 Keyme에서 확인해보세요.", new HashMap<>());
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


}
