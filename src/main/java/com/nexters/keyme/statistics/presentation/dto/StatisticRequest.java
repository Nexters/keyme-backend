package com.nexters.keyme.statistics.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticRequest {
    private StatisticType type;

    public enum StatisticType {
        SIMILAR,
        DIFFERENT;

        @JsonCreator
        public static StatisticType from(String value) {
            return StatisticType.valueOf(value);
        }
    }
}
