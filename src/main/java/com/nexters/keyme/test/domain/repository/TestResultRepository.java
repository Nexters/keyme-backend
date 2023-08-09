package com.nexters.keyme.test.domain.repository;

import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.test.domain.internaldto.TestResultStatisticInfo;
import com.nexters.keyme.test.domain.model.Test;
import com.nexters.keyme.test.domain.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    Optional<TestResult> findByTestAndSolver(Test test, MemberEntity solver);

    @Query(
        "select new com.nexters.keyme.test.domain.internaldto.TestResultStatisticInfo(avg(tr.matchRate), COUNT(*)) from TestResult tr " +
            "where tr.test.testId = :testId"
    )
    Optional<TestResultStatisticInfo> findStatisticsByTestId(Long testId);
}
