package com.nexters.keyme.domain.test.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SendNotificationEvent {
    private Long ownerId;
    private Long solverId;
}
