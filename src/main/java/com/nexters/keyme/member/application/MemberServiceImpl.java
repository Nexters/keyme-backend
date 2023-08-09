package com.nexters.keyme.member.application;

import com.nexters.keyme.auth.domain.internaldto.OAuthUserInfo;
import com.nexters.keyme.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.member.domain.internaldto.MemberModificationInfo;
import com.nexters.keyme.member.domain.internaldto.ValidationInfo;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.model.MemberOAuth;
import com.nexters.keyme.member.domain.model.MemberOAuthId;
import com.nexters.keyme.member.domain.repository.MemberOAuthRepository;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.member.domain.service.NicknameValidator;
import com.nexters.keyme.member.domain.service.ProfileImageService;
import com.nexters.keyme.member.presentation.dto.request.MemberModificationRequest;
import com.nexters.keyme.member.presentation.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.member.presentation.dto.response.NicknameVerificationResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberWithTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberOAuthRepository memberOAuthRepository;
    private final NicknameValidator nicknameValidator;
    private final ProfileImageService profileImageService;


    @Override
    @Transactional
    public MemberWithTokenResponse getOrCreateMember(OAuthUserInfo oauthUserInfo) {
        MemberOAuthId memberOAuthId = new MemberOAuthId(oauthUserInfo);

        Optional<MemberOAuth> existedMemberOAuth = memberOAuthRepository.findById(memberOAuthId);
        if (existedMemberOAuth.isPresent()) {
            return new MemberWithTokenResponse(existedMemberOAuth.get().getMember());
        }

        MemberEntity memberEntity = memberRepository.save(new MemberEntity());
        MemberOAuth memberOauth = memberOAuthRepository.save(
                MemberOAuth.builder()
                        .oauthInfo(memberOAuthId)
                        .member(memberEntity)
                        .build()
        );

        return new MemberWithTokenResponse(memberEntity);
    }

    @Override
    @Transactional
    public MemberResponse getMemberInfo(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(ResourceNotFoundException::new);

        return new MemberResponse(member);
    }

    @Override
    public NicknameVerificationResponse verifyNickname(NicknameVerificationRequest request) {
        ValidationInfo validationInfo = nicknameValidator.validateNickname(request.getNickname());

        return new NicknameVerificationResponse(validationInfo.isValid(), validationInfo.getMessage());
    }

    @Override
    @Transactional
    public MemberResponse modifyMemberInfo(MemberModificationRequest request, UserInfo userInfo) {
        MemberModificationInfo modificationInfo = MemberModificationInfo.builder()
                .nickname(request.getNickname())
                .originalImage(request.getProfileImage())
                .thumbnailImage(request.getProfileThumbnail())
                .build();

        MemberEntity member = memberRepository.findById(userInfo.getMemberId())
                .orElseThrow(ResourceNotFoundException::new);
        member.modifyMemberInfo(modificationInfo);

        return new MemberResponse(member);
    }
}
