package com.nexters.keyme.domain.statistics.domain.service;

import com.nexters.keyme.domain.statistics.dto.internal.CoordinateInfo;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;

import java.util.List;

public interface CoordinateConversionService {
    List<CoordinateInfo> convertFrom(List<Statistic> statistics);
}
