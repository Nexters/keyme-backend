package com.nexters.keyme.global.common.event.message;

import lombok.Getter;

@Getter
public class AddStatisticEvent {
    private Long testOwnerId;
    private Long solverId;
    private Long resultId; // TODO: DTO 변환

    public AddStatisticEvent(Long testOwnerId, Long solverId, Long resultId) {
        this.testOwnerId = testOwnerId;
        this.solverId = solverId;
        this.resultId = resultId;
    }
}
