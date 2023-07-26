package com.nexters.keyme.member.controller;

import com.nexters.keyme.auth.dto.UserInfo;
import com.nexters.keyme.auth.resolver.RequestUser;
import com.nexters.keyme.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nexters.keyme.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Api(tags = "멤버", description = "멤버 관련 API")
public class MemberController {
  private final MemberService memberService;

  @GetMapping
  @ApiOperation(value = "(테스트용) Member Id 가져오기")
  @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
  public Long getMemberId(@RequestUser UserInfo userInfo) {
    return userInfo.getMemberId();
  }
}
