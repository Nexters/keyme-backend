package com.nexters.keyme.member.application;

import com.nexters.keyme.auth.dto.OAuthUserInfo;
import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.member.domain.internaldto.MemberModificationInfo;
import com.nexters.keyme.member.domain.service.NicknameValidationService;
import com.nexters.keyme.member.domain.internaldto.ValidationInfo;
import com.nexters.keyme.member.domain.service.ProfileImageService;
import com.nexters.keyme.member.presentation.dto.MemberModificationRequest;
import com.nexters.keyme.member.presentation.dto.NicknameVerificationRequest;
import com.nexters.keyme.member.presentation.dto.NicknameVerificationResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;
import com.nexters.keyme.member.domain.repository.MemberOAuthRepository;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.model.MemberOAuthEntity;
import com.nexters.keyme.member.domain.model.MemberOAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberOAuthRepository memberOAuthRepository;
    private final NicknameValidationService nicknameValidationService;
    private final ProfileImageService profileImageService;


    @Override
    @Transactional
    public MemberResponse getOrCreateMember(OAuthUserInfo oauthUserInfo) {
        MemberOAuthId memberOAuthId = new MemberOAuthId(oauthUserInfo);

        Optional<MemberOAuthEntity> existedMemberOAuth = memberOAuthRepository.findById(memberOAuthId);
        if (existedMemberOAuth.isPresent()) {
            return new MemberResponse(existedMemberOAuth.get().getMember());
        }

        MemberEntity memberEntity = memberRepository.save(new MemberEntity());
        MemberOAuthEntity memberOAuthEntity = memberOAuthRepository.save(
                MemberOAuthEntity.builder()
                        .oauthInfo(memberOAuthId)
                        .member(memberEntity)
                        .build()
        );

        return new MemberResponse(memberEntity);
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
        ValidationInfo validationInfo = nicknameValidationService.validateNickname(request.getNickname());

        return new NicknameVerificationResponse(validationInfo.isValid(), validationInfo.getMessage());
    }

    @Override
    public MemberResponse modifyMemberInfo(MemberModificationRequest request) {
        MemberModificationInfo modificationInfo = MemberModificationInfo.builder()
                .nickname(request.getNickname())
                .originalImage(request.getImage())
                .thumbnailImage(profileImageService.findThumbnailUrl(request.getImage()))
                .build();

        MemberEntity member = new MemberEntity();
        member.modifyMemberInfo(modificationInfo);

        return new MemberResponse(member);
    }

    @Override
    public List<MemberResponse> searchUser(String keyword) {
        List<MemberEntity> result = new ArrayList<>();

        List<MemberEntity> nicknameResult = memberRepository.findAllByNicknameStartsWith(keyword, 0, 10);
        Optional<MemberEntity> inviteCodeResult = memberRepository.findByInviteCode(keyword);

        inviteCodeResult.ifPresent(result::add);
        result.addAll(nicknameResult);

        return result.stream().map(MemberResponse::new).collect(Collectors.toList());
    }
}
