package com.nexters.keyme.member.domain.repository;

import com.nexters.keyme.member.domain.model.MemberOAuth;
import com.nexters.keyme.member.domain.model.MemberOAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOAuthRepository extends JpaRepository<MemberOAuth, MemberOAuthId> {
}
