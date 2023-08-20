package com.nexters.keyme.domain.member.domain.internaldto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ImageInfo {
    private final String originalUrl;
    private final String thumbnailUrl;
}
