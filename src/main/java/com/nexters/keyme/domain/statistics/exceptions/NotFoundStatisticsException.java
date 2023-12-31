package com.nexters.keyme.domain.statistics.exceptions;

import com.nexters.keyme.domain.statistics.exceptions.code.StatisticsErrorCode;
import com.nexters.keyme.global.common.exceptions.KeymeNotFoundException;

public class NotFoundStatisticsException extends KeymeNotFoundException {
    public NotFoundStatisticsException() {
        super(StatisticsErrorCode.NOT_FOUND_STATISTICS.getMessage(), StatisticsErrorCode.NOT_FOUND_STATISTICS.getCode());
    }
}
