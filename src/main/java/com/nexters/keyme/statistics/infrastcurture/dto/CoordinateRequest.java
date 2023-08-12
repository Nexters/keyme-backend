package com.nexters.keyme.statistics.infrastcurture.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CoordinateRequest {
    List<Double> values;

    public CoordinateRequest(List<Double> values) {
        this.values = values;
    }


}
