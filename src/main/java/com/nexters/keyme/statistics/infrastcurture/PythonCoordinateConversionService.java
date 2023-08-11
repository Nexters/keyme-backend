package com.nexters.keyme.statistics.infrastcurture;

import com.nexters.keyme.statistics.domain.internaldto.CoordinateInfo;
import com.nexters.keyme.statistics.domain.model.Statistic;
import com.nexters.keyme.statistics.domain.service.CoordinateConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PythonCoordinateConversionService implements CoordinateConversionService {
    @Override
    public List<CoordinateInfo> convertFrom(List<Statistic> statistics) {
        return null;
    }
}
