package com.nexters.keyme.domain.member.dto.internal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ImageInfo {
    private final String originalUrl;
    private final String thumbnailUrl;
}
