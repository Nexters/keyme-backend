package com.nexters.keyme.member.presentation.controller;

import com.nexters.keyme.common.dto.ApiResponse;
import com.nexters.keyme.member.presentation.dto.response.ImageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.nexters.keyme.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Api(tags = "이미지", description = "프로필 이미지 관련 API")
public class ProfileImageController {


    @PostMapping
    @ApiOperation(value = "프로필 이미지 업로드")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<ImageResponse>> uploadImage(@RequestPart MultipartFile image) {
        ImageResponse response = new ImageResponse("original url", "thumbnail url");
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "SUCCESS", response));
    }
}
