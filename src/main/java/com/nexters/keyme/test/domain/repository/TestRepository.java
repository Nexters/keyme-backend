package com.nexters.keyme.test.domain.repository;

import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.test.domain.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findFirstByMemberAndIsOnboardingOrderByCreatedAtDesc(MemberEntity member, Boolean isOnboarding);

    Optional<Test> findFirstByMemberOrderByCreatedAtDesc(MemberEntity member);
}
