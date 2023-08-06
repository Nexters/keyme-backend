package com.nexters.keyme.statistics.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryResponse {
    private final String name;
    private final String colorCode;
    private final String imageUrl;
}
