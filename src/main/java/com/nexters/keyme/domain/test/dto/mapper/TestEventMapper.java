package com.nexters.keyme.domain.test.dto.mapper;

import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.global.common.event.message.AddStatisticEvent;
import com.nexters.keyme.global.common.event.message.SendQuestionSolvedNotificationEvent;
import org.springframework.stereotype.Component;

@Component
public class TestEventMapper {
    public AddStatisticEvent toAddStatisticEvent(MemberEntity testOwner, TestResult testResult, Long solverId) {
        return new AddStatisticEvent(testOwner.getId(), solverId, testResult.getTestResultId());
    }

    public SendQuestionSolvedNotificationEvent toSendQuestionSolvedNotificationEvent(MemberEntity testOwner, Long solverId) {
        return new SendQuestionSolvedNotificationEvent(testOwner.getId(), solverId);
    }
}
