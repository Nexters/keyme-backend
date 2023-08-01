package com.nexters.keyme.member.infrastructure;

import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @Override
    public MemberEntity findById(Long id) {
        return null;
    }

    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        return null;
    }
}
