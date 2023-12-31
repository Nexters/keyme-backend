package com.nexters.keyme.domain.test.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResultStatisticInfo {
    private Double averageRate;
    private Long solvedCount;
}
