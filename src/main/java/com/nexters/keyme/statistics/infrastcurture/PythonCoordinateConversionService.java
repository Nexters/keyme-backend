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
        CoordinateInfo info1 = new CoordinateInfo(1, 1, 1);
        CoordinateInfo info2 = new CoordinateInfo(1, 1, 1);
        CoordinateInfo info3 = new CoordinateInfo(1, 1, 1);
        CoordinateInfo info4 = new CoordinateInfo(1, 1, 1);
        CoordinateInfo info5 = new CoordinateInfo(1, 1, 1);

        return List.of(info1, info2, info3, info4, info5);
    }
}
