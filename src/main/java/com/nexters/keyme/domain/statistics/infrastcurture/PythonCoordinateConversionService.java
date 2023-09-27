package com.nexters.keyme.domain.statistics.infrastcurture;

import com.nexters.keyme.domain.statistics.dto.internal.CoordinateInfo;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.domain.service.CoordinateConversionService;
import com.nexters.keyme.domain.statistics.infrastcurture.dto.CoordinateRequest;
import com.nexters.keyme.domain.statistics.infrastcurture.dto.CoordinateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PythonCoordinateConversionService implements CoordinateConversionService {
    @Value("${python-server}")
    private String convertingServerUrl;
    @Override
    public List<CoordinateInfo> convertFrom(List<Statistic> statistics) {
        List<Statistic> sortedStatistic = new ArrayList<>(statistics);
        Collections.sort(sortedStatistic);

        List<Double> collect = sortedStatistic.stream()
                .map(Statistic::getSolverAvgScore)
                .collect(Collectors.toList());

        CoordinateResponse response = WebClient.create().post()
                .uri(convertingServerUrl)
                .bodyValue(new CoordinateRequest(collect))
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve()
                .bodyToFlux(CoordinateResponse.class)
                .toStream()
                .findFirst()
                .orElseThrow(RuntimeException::new);

        return response.getCircles().stream()
                .map((circle) -> new CoordinateInfo(circle.getX(), circle.getY(), circle.getR()))
                .collect(Collectors.toList());
    }
}
