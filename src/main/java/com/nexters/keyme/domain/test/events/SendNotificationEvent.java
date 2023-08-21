package com.nexters.keyme.domain.test.events;

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
