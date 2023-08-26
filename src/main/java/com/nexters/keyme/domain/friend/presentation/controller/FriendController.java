package com.nexters.keyme.domain.friend.presentation.controller;

import com.nexters.keyme.domain.friend.dto.request.FriendAcceptRequest;
import com.nexters.keyme.domain.friend.dto.request.FriendAddRequest;
import com.nexters.keyme.domain.friend.dto.response.FriendResponse;
import com.nexters.keyme.global.common.annotation.RequestUser;
import com.nexters.keyme.global.common.dto.internal.UserInfo;
import com.nexters.keyme.global.common.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
@Api(tags = "친구", description = "친구 관련 API")
public class FriendController {

    @GetMapping
    @ApiOperation(value = "친구 리스트 보기")
    public ResponseEntity<ApiResponse<List<FriendResponse>>> getFriendsList(@RequestUser UserInfo userInfo) {

        FriendResponse response = FriendResponse.builder()
                .memberId(231L)
                .nickname("키미")
                .thumbnailUrl("thumbnail url")
                .build();

        return ResponseEntity.ok(new ApiResponse<>(List.of(response)));
    }

    @PostMapping("/request")
    @ApiOperation(value = "친구 추가 요청 보내기")
    public ResponseEntity<ApiResponse> addFriend(FriendAddRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(null));
    }

    @PostMapping("/response")
    @ApiOperation(value = "친구 추가 요청 응답하기")
    public ResponseEntity<ApiResponse> acceptFriend(FriendAcceptRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(null));
    }
}
