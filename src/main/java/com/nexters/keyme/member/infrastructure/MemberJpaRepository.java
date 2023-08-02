package com.nexters.keyme.member.infrastructure;

import com.nexters.keyme.member.domain.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
}
