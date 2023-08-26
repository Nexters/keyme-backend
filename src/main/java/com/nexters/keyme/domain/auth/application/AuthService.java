package com.nexters.keyme.domain.auth.application;


import com.nexters.keyme.domain.auth.dto.request.LoginRequest;
import com.nexters.keyme.domain.member.dto.response.MemberWithTokenResponse;

public interface AuthService {
    MemberWithTokenResponse getMemberWithToken(LoginRequest request);
}
