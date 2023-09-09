package com.nexters.keyme.domain.member.application;

import com.nexters.keyme.domain.member.dto.request.AddTokenRequest;
import com.nexters.keyme.domain.member.dto.request.DeleteTokenRequest;
import com.nexters.keyme.domain.member.dto.request.MemberModificationRequest;
import com.nexters.keyme.domain.member.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.domain.member.dto.response.ImageResponse;
import com.nexters.keyme.domain.member.dto.response.MemberResponse;
import com.nexters.keyme.domain.member.dto.response.NicknameVerificationResponse;
import com.nexters.keyme.global.common.dto.internal.UserInfo;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
  MemberResponse getMemberInfo(Long memberId);

  NicknameVerificationResponse verifyNickname(NicknameVerificationRequest request);

  MemberResponse modifyMemberInfo(MemberModificationRequest request, UserInfo userInfo);

  ImageResponse uploadImage(MultipartFile image);
  void registerDeviceToken(long userId, AddTokenRequest request);

  void deleteDeviceToken(long memberId, DeleteTokenRequest request);

  void deleteMember(long memberId);
}
