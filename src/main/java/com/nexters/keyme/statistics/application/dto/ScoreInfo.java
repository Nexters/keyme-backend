package com.nexters.keyme.statistics.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScoreInfo {
    private Long ownerId;
    private Long solverId;
    private long questionId;
    private int score;
}
