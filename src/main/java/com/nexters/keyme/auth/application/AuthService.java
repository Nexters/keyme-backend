package com.nexters.keyme.auth.application;


import com.nexters.keyme.auth.presentation.dto.request.LoginRequest;
import com.nexters.keyme.member.presentation.dto.response.MemberWithTokenResponse;

public interface AuthService {
    MemberWithTokenResponse getMemberWithToken(LoginRequest request);
}
