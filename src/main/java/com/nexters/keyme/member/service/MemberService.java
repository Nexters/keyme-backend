package com.nexters.keyme.member.service;

import com.nexters.keyme.member.dto.request.LoginRequestDto;
import com.nexters.keyme.member.dto.response.MemberResponseDto;

public interface MemberService {
  MemberResponseDto getOrCreateMember(LoginRequestDto request);
}
