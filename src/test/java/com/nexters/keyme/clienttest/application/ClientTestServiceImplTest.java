package com.nexters.keyme.clienttest.application;

import com.nexters.keyme.member.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ClientTestServiceTest {
    @Autowired
    private ClientTestService clientTestService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("요청한 사용자 엔티티를 저장소에서 삭제한다")
    void clearMemberTest() {
        Assertions.assertThat(memberRepository.findById(1L).isPresent()).isTrue();
        clientTestService.clearMember(1L);
        Assertions.assertThat(memberRepository.findById(1L).isPresent()).isFalse();
    }
}