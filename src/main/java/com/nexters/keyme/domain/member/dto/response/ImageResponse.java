package com.nexters.keyme.domain.member.dto.response;

import com.nexters.keyme.domain.member.dto.internal.ImageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageResponse {
    @ApiModelProperty(value="원본 URL", example = "original URL")
    private final String originalUrl;
    @ApiModelProperty(value="섬네일 URL", example = "thumbnail URL")
    private final String thumbnailUrl;

    public static ImageResponse from(ImageInfo imageInfo) {
        return new ImageResponse(imageInfo.getOriginalUrl(), imageInfo.getThumbnailUrl());
    }
}
