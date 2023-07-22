package com.nexters.keyme.auth.client;

import com.nexters.keyme.auth.dto.response.AppleAuthKeysResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "apple", url = "${feign.apple.url}")
public interface AppleClient {
  @GetMapping("/auth/keys")
  public AppleAuthKeysResponse getAuthKeys();
}