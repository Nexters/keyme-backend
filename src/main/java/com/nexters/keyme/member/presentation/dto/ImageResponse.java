package com.nexters.keyme.member.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageResponse {
    private final String originalUrl;
    private final String thumbnailUrl;
}
