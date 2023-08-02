package com.nexters.keyme.member.domain.repository;

import com.nexters.keyme.member.domain.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
