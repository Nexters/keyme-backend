package com.nexters.keyme.member.controller;

import com.nexters.keyme.member.dto.request.LoginRequestDto;
import com.nexters.keyme.member.dto.response.MemberResponseDto;
import com.nexters.keyme.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor

public class MemberController {
  private final MemberService memberService;
  @PostMapping("/login")
  @ApiOperation(value = "oauth 로그인", notes = "SDK에서 받은 access token(카카오)/identity token(애플)을 통해 로그인한다.")
  public MemberResponseDto signIn(
          @RequestBody LoginRequestDto request) {
    return memberService.getOrCreateMember(request);
  }
}
