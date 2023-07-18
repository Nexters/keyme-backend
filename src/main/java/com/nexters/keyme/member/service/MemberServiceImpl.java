package com.nexters.keyme.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.auth.dto.AppleJwtBody;
import com.nexters.keyme.auth.util.ApplePublicKeyProvider;
import com.nexters.keyme.auth.util.JwtTokenProvider;
import com.nexters.keyme.common.enums.OAuthType;
import com.nexters.keyme.member.client.AppleClient;
import com.nexters.keyme.member.client.KakaoClient;
import com.nexters.keyme.member.dto.OAuthUserInfo;
import com.nexters.keyme.member.dto.request.LoginRequestDto;
import com.nexters.keyme.member.dto.response.AppleAuthKeysResponseDto;
import com.nexters.keyme.member.dto.response.KakaoUserInfoResponseDto;
import com.nexters.keyme.member.dto.response.MemberResponseDto;
import com.nexters.keyme.member.dto.response.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final ObjectMapper objectMapper;

  private final ApplePublicKeyProvider applePublicKeyProvider;

  private final JwtTokenProvider jwtTokenProvider;
  private final AppleClient appleClient;
  private final KakaoClient kakaoClient;

  @Override
  public MemberResponseDto getOrCreateMember(LoginRequestDto request) {
    OAuthType oauthType = request.getOauthType();
    OAuthUserInfo userInfo;

    if (oauthType.equals(OAuthType.APPLE)) {
      userInfo = verifyAppleToken(request.getToken());
    } else if (oauthType.equals(OAuthType.KAKAO)) {
      userInfo = getKakaoProfile(request.getToken());
    }

    // member 조회 -> 없으면 member 생성
    // JWT 생성
    Long memberId = 0L;
    String jwtToken = jwtTokenProvider.createToken(memberId);
    TokenResponseDto tokenObject = new TokenResponseDto(jwtToken, jwtToken);
    MemberResponseDto memberDto =  MemberResponseDto.builder()
            .id(memberId)
            .token(tokenObject)
            .build();

    // refresh 토큰정책 있으면 member에 저장
    // member + token으로 응답

    return memberDto;
  }

  public OAuthUserInfo verifyAppleToken(String identityToken) {
    AppleAuthKeysResponseDto authKeys = appleClient.getAuthKeys();
    String jwtHeaderString = jwtTokenProvider.extractJwtHeaderString(identityToken);
    PublicKey key = applePublicKeyProvider.getPublicKey(jwtHeaderString, authKeys);
    Boolean isVerified  = jwtTokenProvider.verifyToken(identityToken, key);

    if (!isVerified) {
      System.out.println("유효하지 않은 토큰");
    }

    try {
      String jwtBodyString = jwtTokenProvider.extractJwtBodyString(identityToken);
      AppleJwtBody jwtBody = objectMapper.readValue(jwtBodyString, AppleJwtBody.class);

      return OAuthUserInfo.builder()
              .id(jwtBody.getSub())
              .oauthType(OAuthType.APPLE)
              .build();
    } catch(JsonProcessingException e) {
      System.out.println("apple jwt body 파싱 실패");
    }

    return null;
  }

  public OAuthUserInfo getKakaoProfile(String accessToken) {
    KakaoUserInfoResponseDto userInfo = kakaoClient.getUserProfile("Bearer " + accessToken);

    return OAuthUserInfo.builder()
            .id(userInfo.getId().toString())
            .oauthType(OAuthType.KAKAO)
            .build();
  }
}
