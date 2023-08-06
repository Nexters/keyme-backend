package com.nexters.keyme.auth.presentation.controller;


import com.nexters.keyme.auth.presentation.dto.request.LoginRequest;
import com.nexters.keyme.auth.application.AuthService;
import com.nexters.keyme.member.presentation.dto.response.MemberWithTokenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public MemberWithTokenResponse signUpOrSignIn(@RequestBody LoginRequest request) {
        return authService.getMemberWithToken(request);
    }
}
