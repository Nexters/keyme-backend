package com.nexters.keyme.statistics.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScoreInfo {
    private long ownerId;
    private long questionId;
    private int score;
}
