package com.nexters.keyme.domain.statistics.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticRequest {
    @ApiModelProperty(value="타입(가장 비슷한/가장 다른)")
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
