package com.nexters.keyme.domain.test.domain.repository;

import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.dto.internal.TestResultStatisticInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    Optional<TestResult> findByTestAndSolver(Test test, MemberEntity solver);

    @Query(
        "select new com.nexters.keyme.domain.test.dto.internal.TestResultStatisticInfo(avg(tr.matchRate), COUNT(*)) from TestResult tr " +
            "where tr.test.testId = :testId " +
                "and (tr.solver.id != :testOwnerId or tr.solver.id is null)"
    )
    TestResultStatisticInfo findStatisticsByTestId(Long testId, Long testOwnerId);
}
