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

    @Query(value = "SELECT * FROM statistic st WHERE (st.solver_avg_score < :cursorScore OR st.solver_avg_score = :cursorScore AND st.id > :cursor) AND st.id NOT IN :exceptIds ORDER BY st.match_rate DESC, st.id LIMIT 5", nativeQuery = true)
    List<Statistic> findExceptIdsSortByAvgScore(List<Long> exceptIds, long cursor, double cursorScore);

}
