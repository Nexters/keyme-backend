package com.nexters.keyme.statistics.domain.repository;

import com.nexters.keyme.statistics.domain.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT st FROM Statistic st WHERE st.ownerId = :ownerId AND st.questionId = :questionId")
    Optional<Statistic> findByOwnerIdAndQuestionIdWithLock(long ownerId, long questionId);

    @Query(value = "SELECT * FROM statistic st WHERE st.owner_id = :memberId ORDER BY st.match_rate LIMIT 5", nativeQuery = true)
    List<Statistic> findByMemberIdSortByMatchRateAsc(long memberId);

    @Query(value = "SELECT * FROM statistic st WHERE st.owner_id = :memberId ORDER BY st.match_rate DESC LIMIT 5", nativeQuery = true)
    List<Statistic> findByMemberIdSortByMatchRateDesc(long memberId);

//    @Query("SELECT st FROM Statistic st WHERE st.ownerId = :memberId ORDER BY st.matchRate")
//    List<Statistic> findByMemberId(long memberId, String sortBy);
}
