package com.nexters.keyme.member.domain.internaldto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ImageInfo {
    private final String originalUrl;
    private final String thumbnailUrl;
}
