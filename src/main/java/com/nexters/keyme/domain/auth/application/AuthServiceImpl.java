package com.nexters.keyme.domain.auth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.domain.auth.domain.client.AppleClient;
import com.nexters.keyme.domain.auth.domain.client.KakaoClient;
import com.nexters.keyme.domain.auth.domain.exceptions.InvalidAppleTokenException;
import com.nexters.keyme.domain.auth.domain.internaldto.AppleJwtBodyInfo;
import com.nexters.keyme.domain.auth.domain.internaldto.OAuthUserInfo;
import com.nexters.keyme.domain.auth.presentation.dto.request.LoginRequest;
import com.nexters.keyme.domain.auth.presentation.dto.response.AppleAuthKeysResponse;
import com.nexters.keyme.domain.auth.presentation.dto.response.KakaoUserInfoResponse;
import com.nexters.keyme.domain.auth.presentation.dto.response.TokenResponse;
import com.nexters.keyme.domain.auth.domain.helper.ApplePublicKeyProvider;
import com.nexters.keyme.global.util.JwtTokenProvider;
import com.nexters.keyme.global.enums.OAuthType;
import com.nexters.keyme.domain.member.application.MemberService;
import com.nexters.keyme.domain.member.presentation.dto.response.MemberWithTokenResponse;
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

        return memberResponse;
    }

    private OAuthUserInfo getOAuthInfoOfApple(String identityToken) {
        AppleAuthKeysResponse authKeys = appleClient.getAuthKeys();
        String jwtHeaderString = jwtTokenProvider.extractJwtHeaderString(identityToken);
        PublicKey key = applePublicKeyProvider.getPublicKey(jwtHeaderString, authKeys);
        Boolean isVerified  = jwtTokenProvider.verifyToken(identityToken, key);

        if (!isVerified) {
            throw new InvalidAppleTokenException();
        }

        String jwtBodyString = jwtTokenProvider.extractJwtBodyString(identityToken);
        try {
            AppleJwtBodyInfo jwtBody = objectMapper.readValue(jwtBodyString, AppleJwtBodyInfo.class);

            return OAuthUserInfo.builder()
                    .id(jwtBody.getSub())
                    .oauthType(OAuthType.APPLE)
                    .build();
        } catch(JsonProcessingException e) {
            throw new InvalidAppleTokenException();
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
