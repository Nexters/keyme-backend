package com.nexters.keyme.domain.statistics.exceptions;

import com.nexters.keyme.domain.statistics.exceptions.code.StatisticsErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeBadRequestException;

public class NotEnoughStatisticsException extends KeymeBadRequestException {
    public NotEnoughStatisticsException() {
        super(StatisticsErrorCode.NOT_ENOUGH_STATISTICS.getMessage(), StatisticsErrorCode.NOT_ENOUGH_STATISTICS.getCode());
    }
}
