package com.nexters.keyme.domain.member.presentation.controller;

import com.nexters.keyme.domain.member.application.MemberService;
import com.nexters.keyme.domain.member.presentation.dto.response.ImageResponse;
import com.nexters.keyme.global.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Api(tags = "이미지", description = "프로필 이미지 관련 API")
public class ProfileImageController {
    private final MemberService memberService;

    @PostMapping
    @ApiOperation(value = "프로필 이미지 업로드")
    public ResponseEntity<ApiResponse<ImageResponse>> uploadImage(@RequestPart MultipartFile image) {
        ImageResponse response = memberService.uploadImage(image);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }
}
