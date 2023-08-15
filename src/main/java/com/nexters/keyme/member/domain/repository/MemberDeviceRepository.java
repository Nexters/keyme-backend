package com.nexters.keyme.member.domain.repository;

import com.nexters.keyme.member.domain.model.MemberDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberDeviceRepository extends JpaRepository<MemberDevice, Long> {
    @Query("SELECT md FROM MemberDevice md WHERE md.member.id = :memberId AND md.token = :token")
    Optional<MemberDevice> findByMemberIdAndToken(@Param(value = "memberId") long memberId, @Param(value = "token") String token);
}
