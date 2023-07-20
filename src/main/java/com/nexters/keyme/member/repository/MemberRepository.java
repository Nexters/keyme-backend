package com.nexters.keyme.member.repository;

import com.nexters.keyme.model.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
