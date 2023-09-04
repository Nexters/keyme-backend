package com.nexters.keyme.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProblemSolvedNotificationRequest {
    private Long ownerId;
    private Long solverId;

}
