package com.nexters.keyme.statistics.domain.service;

import com.nexters.keyme.statistics.domain.internaldto.CoordinateInfo;
import com.nexters.keyme.statistics.domain.model.Statistic;

public interface CoordinateConversionService {
    CoordinateInfo convertFrom(Statistic statistic);
}
