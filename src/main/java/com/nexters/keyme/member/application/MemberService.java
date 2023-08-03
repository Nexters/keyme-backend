package com.nexters.keyme.member.application;

import com.nexters.keyme.auth.dto.OAuthUserInfo;
import com.nexters.keyme.member.presentation.dto.MemberModificationRequest;
import com.nexters.keyme.member.presentation.dto.NicknameVerificationRequest;
import com.nexters.keyme.member.presentation.dto.NicknameVerificationResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;

public interface MemberService {
  MemberResponse getOrCreateMember(OAuthUserInfo oauthUserInfo);

  MemberResponse getMemberInfo(Long memberId);

  NicknameVerificationResponse verifyNickname(NicknameVerificationRequest request);

  MemberResponse modifyMemberInfo(MemberModificationRequest request);
}
