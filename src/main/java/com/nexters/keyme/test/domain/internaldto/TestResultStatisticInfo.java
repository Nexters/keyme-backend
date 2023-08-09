package com.nexters.keyme.test.domain.internaldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResultStatisticInfo {
    private double averageRate;
    private Long solvedCount;
}
