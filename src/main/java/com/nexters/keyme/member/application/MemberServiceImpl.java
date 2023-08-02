package com.nexters.keyme.member.application;

import com.nexters.keyme.auth.dto.OAuthUserInfo;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberOAuthRepository memberOAuthRepository;

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
    public MemberResponse getMemberInfo(Long memberId) {
        return null;
    }

    @Override
    public NicknameVerificationResponse verifyNickname(NicknameVerificationRequest request) {
        return null;
    }

    @Override
    public MemberResponse modifyMemberInfo(MemberModificationRequest request) {
        return null;
    }

    @Override
    public List<MemberResponse> searchUser(String keyword) {
        return null;
    }
}
