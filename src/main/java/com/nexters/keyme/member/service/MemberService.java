package com.nexters.keyme.member.service;

import com.nexters.keyme.auth.dto.OAuthUserInfo;
import com.nexters.keyme.member.dto.response.MemberResponse;

public interface MemberService {
  MemberResponse getOrCreateMember(OAuthUserInfo oauthUserInfo);
}
