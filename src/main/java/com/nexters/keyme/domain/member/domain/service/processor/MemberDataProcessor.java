package com.nexters.keyme.domain.member.domain.service.processor;

import com.nexters.keyme.domain.member.domain.model.*;
import com.nexters.keyme.domain.member.domain.repository.MemberDeviceRepository;
import com.nexters.keyme.domain.member.domain.repository.MemberOAuthRepository;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import com.nexters.keyme.domain.test.domain.service.processor.TestDataProcessor;
import com.nexters.keyme.global.common.enums.OAuthType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.nexters.keyme.global.common.constant.ConstantString.DEFAULT_IMAGE_URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberDataProcessor {

    private final MemberOAuthRepository memberOAuthRepository;
    private final MemberDeviceRepository memberDeviceRepository;
    private final MemberRepository memberRepository;
    private final TestDataProcessor testDataProcessor;
    private final TestResultRepository testResultRepository;

    @Transactional
    public Optional<MemberEntity> getMemberByOAuthId(OAuthType oauthType, String id) {
        MemberOAuthId memberOAuthId = new MemberOAuthId(oauthType, id);

        return memberOAuthRepository.findById(memberOAuthId)
                .map(memberOAuth -> memberOAuth.getMember());
    }

    @Transactional
    public MemberEntity createMember(OAuthType oauthType, String id) {
        MemberOAuthId memberOAuthId = new MemberOAuthId(oauthType, id);
        MemberEntity memberEntity = MemberEntity.builder()
                .profileImage(new ProfileImage(DEFAULT_IMAGE_URL, DEFAULT_IMAGE_URL))
                .build();
        memberRepository.save(memberEntity);
        memberOAuthRepository.save(
                MemberOAuth.builder()
                        .oauthInfo(memberOAuthId)
                        .member(memberEntity)
                        .build()
        );

        return memberEntity;
    }

    @Transactional
    public Boolean checkOnboardingClear(MemberEntity member) {
        Test onboardingTest = testDataProcessor.getExistOnboardingTest(member).orElse(null);
        if (onboardingTest == null) return false;

        TestResult testResult = testResultRepository.findByTestAndSolver(onboardingTest, member).orElse(null);

        return testResult != null;
    }

    @Transactional
    public MemberEntity deleteMember(MemberEntity member) {
        member.setDeleted();

        for (MemberOAuth oauth : member.getMemberOauth()) {
            memberOAuthRepository.deleteById(oauth.getOauthInfo());
        }

        for (MemberDevice device : member.getMemberDevice()) {
            memberDeviceRepository.deleteById(device.getId());
        }

        return member;
    }
}
