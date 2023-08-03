package com.nexters.keyme.member.domain.repository;

import com.nexters.keyme.member.domain.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByNickname(String nickname);

    @Query(
        value = "SELECT * FROM member m WHERE m.nickname LIKE ':nickname%' AND m.id > :cursorId limit 10 ORDER BY m.nickname",
        nativeQuery = true)
    List<MemberEntity> findAllByNicknameStartsWith(String nickname, int cursorId, int limit);

    Optional<MemberEntity> findByInviteCode(String inviteCode);
}
