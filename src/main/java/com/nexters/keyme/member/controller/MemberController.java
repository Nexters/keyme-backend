package com.nexters.keyme.member.controller;

import com.nexters.keyme.auth.dto.UserInfo;
import com.nexters.keyme.auth.resolver.RequestUser;
import com.nexters.keyme.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @GetMapping
  public Long getMemberId(@RequestUser UserInfo userInfo) {
    return userInfo.getMemberId();
  }
}
