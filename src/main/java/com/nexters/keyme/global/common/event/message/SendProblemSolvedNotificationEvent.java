package com.nexters.keyme.global.common.event.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SendProblemSolvedNotificationEvent {
    private Long ownerId;
    private Long solverId;
}
