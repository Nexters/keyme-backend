package com.nexters.keyme.domain.member.domain.repository;

import com.nexters.keyme.domain.member.exceptions.NotFoundMemberException;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.test.annotation.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RepositoryTest
@Sql("/member.sql")
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

//    @BeforeEach
//    void beforeEach() {
//        MemberEntity member = MemberEntity.builder()
//                .id(1L)
//                .nickname("nick")
//                .friendCode("AZ03A20")
//                .build();
//
//        memberRepository.save(member);
//    }

    @Test
    @DisplayName("닉네임으로 멤버 엔티티 찾기 테스트")
    void findByNickname() {
        assertThatThrownBy(() -> {
            memberRepository.findByNickname("tom").orElseThrow(NotFoundMemberException::new);
        }).isInstanceOf(NotFoundMemberException.class);

        MemberEntity member = memberRepository.findByNickname("nick")
                .orElseThrow(NotFoundMemberException::new);

        assertThat(member.getNickname()).isEqualTo("nick");
    }

    @Test
    @DisplayName("초대코드로 멤버 엔티티 찾기 테스트")
    void findByInviteCode() {
        assertThatThrownBy(() -> {
            memberRepository.findByFriendCode("ZXCVBNV").orElseThrow(NotFoundMemberException::new);
        }).isInstanceOf(NotFoundMemberException.class);

        MemberEntity member = memberRepository.findByFriendCode("1234567")
                .orElseThrow(NotFoundMemberException::new);

        assertThat(member.getFriendCode()).isEqualTo("1234567");
    }
}