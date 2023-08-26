package com.nexters.keyme.domain.auth.domain.client;

import com.nexters.keyme.domain.auth.dto.response.AppleAuthKeysResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "apple", url = "${feign.apple.url}")
public interface AppleClient {
  @GetMapping("/auth/keys")
  public AppleAuthKeysResponse getAuthKeys();
}