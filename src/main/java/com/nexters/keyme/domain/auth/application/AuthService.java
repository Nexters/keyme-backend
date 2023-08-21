package com.nexters.keyme.domain.auth.application;


import com.nexters.keyme.domain.auth.presentation.dto.request.LoginRequest;
import com.nexters.keyme.domain.member.presentation.dto.response.MemberWithTokenResponse;

public interface AuthService {
    MemberWithTokenResponse getMemberWithToken(LoginRequest request);
}
