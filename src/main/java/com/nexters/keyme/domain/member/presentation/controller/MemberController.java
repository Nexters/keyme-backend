package com.nexters.keyme.domain.member.presentation.controller;

import com.nexters.keyme.global.common.dto.internal.UserInfo;
import com.nexters.keyme.domain.member.application.MemberService;
import com.nexters.keyme.domain.member.presentation.dto.request.AddTokenRequest;
import com.nexters.keyme.domain.member.presentation.dto.request.DeleteTokenRequest;
import com.nexters.keyme.domain.member.presentation.dto.request.MemberModificationRequest;
import com.nexters.keyme.domain.member.presentation.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.global.common.annotation.ApiSecurityIgnore;
import com.nexters.keyme.global.common.annotation.RequestUser;
import com.nexters.keyme.global.common.dto.response.ApiResponse;
import com.nexters.keyme.domain.member.presentation.dto.response.MemberResponse;
import com.nexters.keyme.domain.member.presentation.dto.response.NicknameVerificationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Api(tags = "멤버", description = "멤버 관련 API")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    @ApiOperation(value = "본인 정보 가져오기")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberInfo(@RequestUser UserInfo requestUser) {
        MemberResponse response = memberService.getMemberInfo(requestUser.getMemberId());
        return ResponseEntity.ok(new ApiResponse<>(response));
    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "회원 정보 보기")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberInfo(@PathVariable(name = "memberId") Long memberId) {
        MemberResponse response = memberService.getMemberInfo(memberId);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }

    @PostMapping("/verify-nickname")
    @ApiOperation("닉네임 중복 확인")
    @ApiSecurityIgnore
    public ResponseEntity<ApiResponse<NicknameVerificationResponse>> verifyNickname(@RequestBody NicknameVerificationRequest request) {
        NicknameVerificationResponse response = memberService.verifyNickname(request);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }

    @PatchMapping
    @ApiOperation("회원 정보 수정")
    public ResponseEntity<ApiResponse<MemberResponse>> modifyMemberInfo(@RequestBody MemberModificationRequest request, @RequestUser UserInfo userInfo) {
        MemberResponse response = memberService.modifyMemberInfo(request, userInfo);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }

    @PostMapping("/devices")
    @ApiOperation("멤버 FCM 토큰 추가")
    public ResponseEntity<ApiResponse> addDeviceToken(@RequestUser UserInfo userInfo, @RequestBody AddTokenRequest request) {
        memberService.registerDeviceToken(userInfo.getMemberId(), request);
        return ResponseEntity.ok(new ApiResponse<>(200, "SUCCESS", null));
    }

    @DeleteMapping("/devices")
    @ApiOperation("멤버 FCM 토큰 추가")
    public ResponseEntity<ApiResponse> deleteDeviceToken(@RequestUser UserInfo userInfo, @RequestBody DeleteTokenRequest request) {
        memberService.deleteDeviceToken(userInfo.getMemberId(), request);
        return ResponseEntity.ok(new ApiResponse<>(200, "SUCCESS", null));
    }

}