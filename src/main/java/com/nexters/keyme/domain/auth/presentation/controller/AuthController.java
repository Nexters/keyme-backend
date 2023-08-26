package com.nexters.keyme.domain.auth.presentation.controller;


import com.nexters.keyme.domain.auth.dto.request.LoginRequest;
import com.nexters.keyme.domain.auth.application.AuthService;
import com.nexters.keyme.global.common.annotation.ApiSecurityIgnore;
import com.nexters.keyme.global.common.dto.response.ApiResponse;
import com.nexters.keyme.domain.member.dto.response.MemberWithTokenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "인증", description = "로그인/회원가입 API")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "회원가입/로그인 API")
    @ApiSecurityIgnore
    public ResponseEntity<ApiResponse<MemberWithTokenResponse>>  signUpOrSignIn(@RequestBody LoginRequest request) {
        MemberWithTokenResponse memberWithTokenResponse = authService.getMemberWithToken(request);
        return ResponseEntity.ok(new ApiResponse<>(memberWithTokenResponse) );
    }
}
