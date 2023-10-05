package com.nexters.keyme.domain.statistics.domain.repository;

import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Query("SELECT st FROM Statistic st WHERE st.ownerId = :ownerId AND st.questionId = :questionId")
    Optional<Statistic> findByOwnerIdAndQuestionId(@Param(value = "ownerId") long ownerId, @Param(value = "questionId") long questionId);

    @Query(value = "SELECT * FROM statistic st WHERE st.owner_id = :memberId AND solver_count > 0 ORDER BY st.match_rate LIMIT 5", nativeQuery = true)
    List<Statistic> findByMemberIdSortByMatchRateAsc(@Param(value = "memberId") long memberId);

    @Query(value = "SELECT * FROM statistic st WHERE st.owner_id = :memberId AND solver_count > 0 ORDER BY st.match_rate DESC LIMIT 5", nativeQuery = true)
    List<Statistic> findByMemberIdSortByMatchRateDesc(@Param(value = "memberId") long memberId);

    @Query(value = "SELECT * FROM statistic st WHERE (st.solver_avg_score < :cursorScore OR st.solver_avg_score = :cursorScore AND st.id > :cursor) AND st.id NOT IN :exceptIds AND st.owner_id = :memberId ORDER BY st.solver_avg_score DESC, st.id LIMIT :limit", nativeQuery = true)
    List<Statistic> findExceptIdsSortByAvgScore(long memberId, @Param(value = "exceptIds") List<Long> exceptIds,
                                                @Param(value = "cursor") long cursor,
                                                @Param(value = "cursorScore") double cursorScore,
                                                @Param(value = "limit") int limit);
    @Query("SELECT st FROM Statistic st WHERE st.id = :id")
    Optional<Statistic> findByIdWithLock(@Param(value = "id") long id);
}
