package com.nexters.keyme.member.repository;

import com.nexters.keyme.member.domain.MemberOAuthEntity;
import com.nexters.keyme.member.domain.MemberOAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOAuthRepository extends JpaRepository<MemberOAuthEntity, MemberOAuthId> {
}
