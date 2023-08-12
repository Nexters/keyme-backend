package com.nexters.keyme.statistics.domain.repository;

import com.nexters.keyme.statistics.domain.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Query("SELECT st FROM Statistic st WHERE st.ownerId = :ownerId AND st.questionId = :questionId")
    Optional<Statistic> findByOwnerIdAndQuestionIdWithLock(long ownerId, long questionId);

    @Query("SELECT st FROM Statistic st WHERE st.ownerId = :memberId ORDER BY st.matchRate")
    List<Statistic> findByMemberIdSortByMatchRateAsc(long memberId);

    @Query("SELECT st FROM Statistic st WHERE st.ownerId = :memberId ORDER BY st.matchRate DESC")
    List<Statistic> findByMemberIdSortByMatchRateDesc(long memberId);

//    @Query("SELECT st FROM Statistic st WHERE st.ownerId = :memberId ORDER BY st.matchRate")
//    List<Statistic> findByMemberId(long memberId, String sortBy);
}
