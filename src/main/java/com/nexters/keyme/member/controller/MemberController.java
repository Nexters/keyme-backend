package com.nexters.keyme.member.controller;

import com.nexters.keyme.member.dto.request.LoginRequestDto;
import com.nexters.keyme.member.dto.response.MemberResponseDto;
import com.nexters.keyme.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  @PostMapping
  public MemberResponseDto signIn(
          @RequestBody LoginRequestDto request) {
    return memberService.getOrCreateMember(request);
  }
}
