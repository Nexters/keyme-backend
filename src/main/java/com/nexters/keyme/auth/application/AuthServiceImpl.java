package com.nexters.keyme.auth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.auth.domain.client.AppleClient;
import com.nexters.keyme.auth.domain.client.KakaoClient;
import com.nexters.keyme.auth.domain.internaldto.AppleJwtBodyInfo;
import com.nexters.keyme.auth.domain.internaldto.OAuthUserInfo;
import com.nexters.keyme.auth.presentation.dto.request.LoginRequest;
import com.nexters.keyme.auth.presentation.dto.response.AppleAuthKeysResponse;
import com.nexters.keyme.auth.presentation.dto.response.KakaoUserInfoResponse;
import com.nexters.keyme.auth.presentation.dto.response.TokenResponse;
import com.nexters.keyme.auth.domain.helper.ApplePublicKeyProvider;
import com.nexters.keyme.common.util.JwtTokenProvider;
import com.nexters.keyme.common.enums.OAuthType;
import com.nexters.keyme.member.application.MemberService;
import com.nexters.keyme.member.presentation.dto.response.MemberWithTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ObjectMapper objectMapper;
    private final ApplePublicKeyProvider applePublicKeyProvider;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppleClient appleClient;
    private final KakaoClient kakaoClient;
    private final MemberService memberService;

    @Override
    public MemberWithTokenResponse getMemberWithToken(LoginRequest request) {
        OAuthType oauthType = request.getOauthType();
        OAuthUserInfo userInfo = null;

        if (oauthType.equals(OAuthType.APPLE)) {
            userInfo = getOAuthInfoOfApple(request.getToken());
        } else if (oauthType.equals(OAuthType.KAKAO)) {
            userInfo = getOAuthInfoOfKakao(request.getToken());
        }

        MemberWithTokenResponse memberResponse = memberService.getOrCreateMember(userInfo);
        String jwtToken = jwtTokenProvider.createToken(memberResponse.getId());
        TokenResponse tokenObject = new TokenResponse(jwtToken);
        memberResponse.setToken(tokenObject);

        // refresh 토큰정책 있으면 member에 저장

        return memberResponse;
    }

    private OAuthUserInfo getOAuthInfoOfApple(String identityToken) {
        AppleAuthKeysResponse authKeys = appleClient.getAuthKeys();
        String jwtHeaderString = jwtTokenProvider.extractJwtHeaderString(identityToken);
        PublicKey key = applePublicKeyProvider.getPublicKey(jwtHeaderString, authKeys);
        Boolean isVerified  = jwtTokenProvider.verifyToken(identityToken, key);

        if (!isVerified) { throw new RuntimeException(); }

        try {
            String jwtBodyString = jwtTokenProvider.extractJwtBodyString(identityToken);
            AppleJwtBodyInfo jwtBody = objectMapper.readValue(jwtBodyString, AppleJwtBodyInfo.class);

            return OAuthUserInfo.builder()
                    .id(jwtBody.getSub())
                    .oauthType(OAuthType.APPLE)
                    .build();
        } catch(JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    private OAuthUserInfo getOAuthInfoOfKakao(String accessToken) {
        KakaoUserInfoResponse userInfo = kakaoClient.getUserProfile("Bearer " + accessToken);

        return OAuthUserInfo.builder()
                .id(userInfo.getId().toString())
                .oauthType(OAuthType.KAKAO)
                .build();
    }
}