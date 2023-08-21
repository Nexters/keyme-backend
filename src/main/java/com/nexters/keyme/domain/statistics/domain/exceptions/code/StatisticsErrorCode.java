package com.nexters.keyme.domain.statistics.domain.exceptions.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatisticsErrorCode {
    NOT_ENOUGH_STATISTICS(400, "통계 정보가 충분하지 않습니다."),
    NOT_FOUND_STATISTICS(404, "통계정보를 찾을 수 없습니다.");

    private final int code;
    private final String message;
}
