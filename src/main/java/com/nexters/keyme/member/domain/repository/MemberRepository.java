package com.nexters.keyme.member.domain.repository;

import com.nexters.keyme.member.domain.model.MemberEntity;

public interface MemberRepository {
    MemberEntity findById(Long id);
    MemberEntity save(MemberEntity memberEntity);

}
