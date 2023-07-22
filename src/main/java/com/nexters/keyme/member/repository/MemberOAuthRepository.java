package com.nexters.keyme.member.repository;

import com.nexters.keyme.model.member.MemberOAuthEntity;
import com.nexters.keyme.model.member.MemberOAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOAuthRepository extends JpaRepository<MemberOAuthEntity, MemberOAuthId> {
}
