package com.nexters.keyme.member.domain.repository;

import com.nexters.keyme.domain.member.domain.repository.MemberDeviceRepository;
import com.nexters.keyme.global.exceptions.ResourceNotFoundException;
import com.nexters.keyme.domain.member.domain.model.MemberDevice;
import com.nexters.keyme.test.annotation.RepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RepositoryTest
class MemberDeviceRepositoryTest {
    @Autowired
    private MemberDeviceRepository memberDeviceRepository;

    @Test
    @DisplayName("멤버 id와 토큰으로 Device 조회")
    void findByMemberIdAndToken() {
        MemberDevice device = memberDeviceRepository.findByMemberIdAndToken(1L, "token1")
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertThat(device.getId()).isEqualTo(1L);
        Assertions.assertThat(device.getToken()).isEqualTo("token1");
    }

    @Test
    @DisplayName("멤버 id 리스트로 Device 조회")
    void findAllByMemberIdsTest() {
        List<MemberDevice> devices = memberDeviceRepository.findAllByMemberIds(List.of(1L));

        Assertions.assertThat(devices.size()).isEqualTo(3);
        Assertions.assertThat(devices.get(0).getToken()).isEqualTo("token1");
    }

    @Test
    @DisplayName("멤버 id로 Device 조회")
    void findAllByMemberIdTest() {
        List<MemberDevice> devices = memberDeviceRepository.findAllByMemberId(1L);

        Assertions.assertThat(devices.size()).isEqualTo(3);
        Assertions.assertThat(devices.get(0).getToken()).isEqualTo("token1");
    }
}