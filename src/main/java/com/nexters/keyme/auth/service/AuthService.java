package com.nexters.keyme.auth.service;


import com.nexters.keyme.auth.dto.request.LoginRequest;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;

public interface AuthService {
    MemberResponse getMemberWithToken(LoginRequest request);
}
