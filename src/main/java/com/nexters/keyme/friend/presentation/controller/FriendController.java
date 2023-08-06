package com.nexters.keyme.friend.presentation.controller;

import com.nexters.keyme.auth.dto.UserInfo;
import com.nexters.keyme.auth.resolver.RequestUser;
import com.nexters.keyme.common.dto.ApiResponse;
import com.nexters.keyme.friend.presentation.dto.FriendAcceptRequest;
import com.nexters.keyme.friend.presentation.dto.FriendAddRequest;
import com.nexters.keyme.friend.presentation.dto.FriendResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.nexters.keyme.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
@Api(tags = "친구", description = "친구 관련 API")
public class FriendController {

    @GetMapping
    @ApiOperation(value = "친구 리스트 보기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<List<FriendResponse>>> getFriendsList(@RequestUser UserInfo userInfo) {

        FriendResponse response = FriendResponse.builder()
                .memberId(231L)
                .nickname("키미")
                .thumbnailUrl("thumbnail url")
                .build();

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "SUCCESS", List.of(response)));
    }

    @PostMapping("/request")
    @ApiOperation(value = "친구 추가 요청 보내기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse> addFriend(FriendAddRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "친구 추가 요청이 완료되었습니다.", null));
    }

    @PostMapping("/response")
    @ApiOperation(value = "친구 추가 요청 응답하기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse> acceptFriend(FriendAcceptRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "친구 추가 요청이 완료되었습니다.", null));
    }
}
