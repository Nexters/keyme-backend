package com.nexters.keyme.domain.member.domain.repository;

import com.nexters.keyme.domain.member.domain.model.MemberOAuth;
import com.nexters.keyme.domain.member.domain.model.MemberOAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOAuthRepository extends JpaRepository<MemberOAuth, MemberOAuthId> {
}
