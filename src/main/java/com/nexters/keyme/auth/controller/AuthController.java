package com.nexters.keyme.auth.controller;


import com.nexters.keyme.auth.service.AuthService;
import com.nexters.keyme.member.dto.request.LoginRequest;
import com.nexters.keyme.member.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public MemberResponse signUpOrSignIn(@RequestBody LoginRequest request) {
        return authService.getMemberWithToken(request);
    }
}
