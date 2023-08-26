package com.nexters.keyme.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class KakaoUserInfoResponse {
  private Long id;

  @JsonProperty("connected_at")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Seoul")
  private LocalDateTime connectedAt;

  @JsonProperty("kakao_account")
  private KakaoAccount kakaoAccount;

  @Getter
  static public class KakaoAccount {
    @JsonProperty("profile_nickname_needs_agreement")
    private Boolean profileNicknameNeedsAgreement;

    private KakaoProfile profile;

    @Getter
    static public class KakaoProfile {
      private String nickname;

      @JsonProperty("thumbnail_image_url")
      private String thumbnailImageUrl;

      @JsonProperty("profile_image_url")
      private String profileImageUrl;

      @JsonProperty("is_default_image")
      private Boolean isDefaultImage;
    }
  }
}