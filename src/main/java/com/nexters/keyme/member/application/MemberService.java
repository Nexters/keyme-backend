package com.nexters.keyme.member.application;

import com.nexters.keyme.auth.domain.internaldto.OAuthUserInfo;
import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.member.presentation.dto.request.MemberModificationRequest;
import com.nexters.keyme.member.presentation.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.member.presentation.dto.response.ImageResponse;
import com.nexters.keyme.member.presentation.dto.response.NicknameVerificationResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberWithTokenResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
  MemberWithTokenResponse getOrCreateMember(OAuthUserInfo oauthUserInfo);

  MemberResponse getMemberInfo(Long memberId);

  NicknameVerificationResponse verifyNickname(NicknameVerificationRequest request);

  MemberResponse modifyMemberInfo(MemberModificationRequest request, UserInfo userInfo);

  ImageResponse uploadImage(MultipartFile image);
}
