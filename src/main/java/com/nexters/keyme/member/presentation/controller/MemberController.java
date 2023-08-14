package com.nexters.keyme.member.presentation.controller;

import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.common.annotation.ApiSecurityIgnore;
import com.nexters.keyme.common.annotation.RequestUser;
import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.member.application.MemberService;
import com.nexters.keyme.member.presentation.dto.request.MemberModificationRequest;
import com.nexters.keyme.member.presentation.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;
import com.nexters.keyme.member.presentation.dto.response.NicknameVerificationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nexters.keyme.common.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

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
    public ResponseEntity<ApiResponse<NicknameVerificationResponse>> verifyNickname(NicknameVerificationRequest request) {
        NicknameVerificationResponse response = memberService.verifyNickname(request);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }

    @PatchMapping
    @ApiOperation("회원 정보 수정")
    public ResponseEntity<ApiResponse<MemberResponse>> modifyMemberInfo(@RequestBody MemberModificationRequest request, @RequestUser UserInfo userInfo) {
        MemberResponse response = memberService.modifyMemberInfo(request, userInfo);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }
}
