package com.nexters.keyme.member.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageResponse {
    @ApiModelProperty(value="원본 URL")
    private final String originalUrl;
    @ApiModelProperty(value="섬네일 URL")
    private final String thumbnailUrl;
}
