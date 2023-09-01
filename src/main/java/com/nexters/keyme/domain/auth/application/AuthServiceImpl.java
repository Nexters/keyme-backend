package com.nexters.keyme.domain.auth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.domain.auth.domain.client.AppleClient;
import com.nexters.keyme.domain.auth.domain.client.KakaoClient;
import com.nexters.keyme.domain.auth.exceptions.InvalidAppleTokenException;
import com.nexters.keyme.domain.auth.dto.internal.AppleJwtBodyInfo;
import com.nexters.keyme.domain.auth.dto.internal.OAuthUserInfo;
import com.nexters.keyme.domain.auth.dto.request.LoginRequest;
import com.nexters.keyme.domain.auth.dto.response.AppleAuthKeysResponse;
import com.nexters.keyme.domain.auth.dto.response.KakaoUserInfoResponse;
import com.nexters.keyme.domain.auth.dto.response.TokenResponse;
import com.nexters.keyme.domain.auth.domain.service.processor.ApplePublicKeyProcessor;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.service.processor.MemberDataProcessor;
import com.nexters.keyme.global.common.util.JwtTokenProvider;
import com.nexters.keyme.global.common.enums.OAuthType;
import com.nexters.keyme.domain.member.application.MemberService;
import com.nexters.keyme.domain.member.dto.response.MemberWithTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ObjectMapper objectMapper;
    private final ApplePublicKeyProcessor applePublicKeyProcessor;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppleClient appleClient;
    private final KakaoClient kakaoClient;
    private final MemberDataProcessor memberDataProcessor;


    @Override
    public MemberWithTokenResponse getMemberWithToken(LoginRequest request) {
        OAuthType oauthType = request.getOauthType();
        OAuthUserInfo userInfo;

        if (oauthType.equals(OAuthType.APPLE)) {
            userInfo = getOAuthInfoOfApple(request.getToken());
        } else if (oauthType.equals(OAuthType.KAKAO)) {
            userInfo = getOAuthInfoOfKakao(request.getToken());
        } else {
            userInfo = null;
        }

        MemberEntity member = memberDataProcessor.getMemberByOAuthId(userInfo.getOauthType(), userInfo.getId()).orElseGet(null);
        if (member == null) member = memberDataProcessor.createMember(userInfo.getOauthType(), userInfo.getId());

        TokenResponse tokenResponse = new TokenResponse(jwtTokenProvider.createToken(member.getId()));
        Boolean isOnboardingClear = memberDataProcessor.checkOnboardingClear(member);

        MemberWithTokenResponse memberWithTokenResponse = new MemberWithTokenResponse(member);
        memberWithTokenResponse.setIsOnboardingClear(isOnboardingClear);
        memberWithTokenResponse.setToken(tokenResponse);
        
        return memberWithTokenResponse;
    }

    private OAuthUserInfo getOAuthInfoOfApple(String identityToken) {
        AppleAuthKeysResponse authKeys = appleClient.getAuthKeys();
        String jwtHeaderString = jwtTokenProvider.extractJwtHeaderString(identityToken);
        PublicKey key = applePublicKeyProcessor.getPublicKey(jwtHeaderString, authKeys);
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
