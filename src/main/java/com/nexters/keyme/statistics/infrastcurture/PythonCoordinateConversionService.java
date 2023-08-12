package com.nexters.keyme.statistics.infrastcurture;

import com.nexters.keyme.statistics.domain.internaldto.CoordinateInfo;
import com.nexters.keyme.statistics.domain.model.Statistic;
import com.nexters.keyme.statistics.domain.service.CoordinateConversionService;
import com.nexters.keyme.statistics.infrastcurture.dto.CoordinateRequest;
import com.nexters.keyme.statistics.infrastcurture.dto.CoordinateResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PythonCoordinateConversionService implements CoordinateConversionService {
    @Override
    public List<CoordinateInfo> convertFrom(List<Statistic> statistics) {
        List<Double> collect = statistics.stream()
                .map((s) -> Double.valueOf(s.getMatchRate()))
                .collect(Collectors.toList());

        CoordinateResponse response = WebClient.create().post()
                .uri("http://101.101.216.41:8000/circle")
                .bodyValue(new CoordinateRequest(collect))
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve()
                .bodyToFlux(CoordinateResponse.class)
                .toStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

        return response.getCircles().stream()
                .map((circle) -> new CoordinateInfo(circle.getX(), circle.getY(), circle.getR()))
                .collect(Collectors.toList());
    }
}
