package com.nexters.keyme.domain.member.application;

import com.nexters.keyme.domain.auth.dto.internal.OAuthUserInfo;
import com.nexters.keyme.domain.member.exceptions.NotFoundMemberException;
import com.nexters.keyme.global.common.dto.internal.UserInfo;
import com.nexters.keyme.domain.member.domain.model.*;
import com.nexters.keyme.domain.member.dto.internal.ImageInfo;
import com.nexters.keyme.domain.member.dto.internal.MemberModificationInfo;
import com.nexters.keyme.domain.member.dto.internal.ValidationInfo;
import com.nexters.keyme.domain.member.domain.repository.MemberDeviceRepository;
import com.nexters.keyme.domain.member.domain.repository.MemberOAuthRepository;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import com.nexters.keyme.domain.member.domain.service.processor.ImageUploader;
import com.nexters.keyme.domain.member.domain.service.validator.NicknameValidator;
import com.nexters.keyme.domain.member.dto.request.AddTokenRequest;
import com.nexters.keyme.domain.member.dto.request.DeleteTokenRequest;
import com.nexters.keyme.domain.member.dto.request.MemberModificationRequest;
import com.nexters.keyme.domain.member.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.domain.member.dto.response.ImageResponse;
import com.nexters.keyme.domain.member.dto.response.MemberResponse;
import com.nexters.keyme.domain.member.dto.response.MemberWithTokenResponse;
import com.nexters.keyme.domain.member.dto.response.NicknameVerificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.nexters.keyme.global.common.constant.ConstantString.DEFAULT_IMAGE_URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberDeviceRepository memberDeviceRepository;
    private final NicknameValidator nicknameValidator;
    private final ImageUploader imageUploader;

    @Override
    @Transactional
    public MemberResponse getMemberInfo(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        return new MemberResponse(member);
    }

    @Override
    @Transactional
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

        nicknameValidator.validateNickname(modificationInfo.getNickname());

        MemberEntity member = memberRepository.findById(userInfo.getMemberId())
                .orElseThrow(NotFoundMemberException::new);
        member.modifyMemberInfo(modificationInfo);

        return new MemberResponse(member);
    }

    @Override
    public ImageResponse uploadImage(MultipartFile image) {
        ImageInfo imageInfo = imageUploader.uploadImage(image);
        return ImageResponse.from(imageInfo);
    }


    @Transactional
    @Override
    public void registerDeviceToken(long userId, AddTokenRequest request) {
        MemberEntity member = memberRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);

        if (isAlreadyExists(member.getMemberDevice(), request.getToken())) {
            log.info("Registering FCM token --- token already exists: {}", request.getToken());
            return;
        }

        MemberDevice device = MemberDevice
                .builder()
                .member(member)
                .token(request.getToken())
                .build();

        memberDeviceRepository.save(device);
    }

    private boolean isAlreadyExists(List<MemberDevice> memberDevice, String token) {
        for (MemberDevice device : memberDevice) {
            if (device.getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteDeviceToken(long memberId, DeleteTokenRequest request) {
        Optional<MemberDevice> device = memberDeviceRepository.findByMemberIdAndToken(memberId, request.getToken());
        if (device.isPresent()) {
            memberDeviceRepository.delete(device.get());
        }
    }
}
