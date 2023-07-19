package com.nexters.keyme.member.client;

import com.nexters.keyme.member.dto.response.KakaoUserInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao", url = "${feign.kakao.url}")
public interface KakaoClient {
  @GetMapping("/v2/user/me")
  public KakaoUserInfoResponseDto getUserProfile(@RequestHeader("Authorization") String accessToken);
}
