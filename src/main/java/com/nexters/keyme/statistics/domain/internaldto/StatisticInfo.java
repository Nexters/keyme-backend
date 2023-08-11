package com.nexters.keyme.statistics.domain.internaldto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StatisticInfo {
    private long ownerId;
    private List<QuestionInfo> questions;

    @Getter
    private class QuestionInfo {
        private long questionId;
        private int ownerScore;
    }
}
