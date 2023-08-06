package com.nexters.keyme.statistics.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoordinateResponse {
    private final double x;
    private final double y;
    private final double r;
}
