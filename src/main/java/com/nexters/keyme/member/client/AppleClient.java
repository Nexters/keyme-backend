package com.nexters.keyme.member.client;

import com.nexters.keyme.member.dto.response.AppleAuthKeysResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "apple", url = "${feign.apple.url}")
public interface AppleClient {
  @GetMapping("/auth/keys")
  public AppleAuthKeysResponseDto getAuthKeys();
}