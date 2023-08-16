package com.nexters.keyme.test.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class SendNotificationEvent {
    private List<Long> userIds;
}
