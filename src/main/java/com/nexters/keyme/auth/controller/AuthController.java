package com.nexters.keyme.auth.controller;


import com.nexters.keyme.auth.service.AuthService;
import com.nexters.keyme.auth.dto.request.LoginRequest;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;
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
    public MemberResponse signUpOrSignIn(@RequestBody LoginRequest request) {
        return authService.getMemberWithToken(request);
    }
}
