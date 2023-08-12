package com.nexters.keyme.statistics.infrastcurture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateResponse {
    private String message;
    private List<CircleReponse> circles;
}
