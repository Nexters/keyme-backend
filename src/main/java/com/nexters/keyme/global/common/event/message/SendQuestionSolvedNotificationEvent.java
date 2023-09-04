package com.nexters.keyme.global.common.event.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SendQuestionSolvedNotificationEvent {
    private Long ownerId;
    private Long solverId;
}
