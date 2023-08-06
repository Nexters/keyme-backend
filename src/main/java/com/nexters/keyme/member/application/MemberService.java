package com.nexters.keyme.member.application;

import com.nexters.keyme.auth.domain.internaldto.OAuthUserInfo;
import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.member.presentation.dto.request.MemberModificationRequest;
import com.nexters.keyme.member.presentation.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.member.presentation.dto.response.NicknameVerificationResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberWithTokenResponse;

public interface MemberService {
  MemberWithTokenResponse getOrCreateMember(OAuthUserInfo oauthUserInfo);

  MemberResponse getMemberInfo(Long memberId);

  NicknameVerificationResponse verifyNickname(NicknameVerificationRequest request);

  MemberResponse modifyMemberInfo(MemberModificationRequest request, UserInfo userInfo);
}
