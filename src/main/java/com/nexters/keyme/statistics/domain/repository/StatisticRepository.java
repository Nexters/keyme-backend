package com.nexters.keyme.statistics.domain.repository;

import com.nexters.keyme.statistics.domain.model.Statistic;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository {

    Optional<Statistic> findByTestIdAndQuestionIdWithLock(long testId, long questionId);

    List<Statistic> findByMemberId(long memberId, String sortBy);
}
