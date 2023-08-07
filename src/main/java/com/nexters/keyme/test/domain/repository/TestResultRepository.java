package com.nexters.keyme.test.domain.repository;

import com.nexters.keyme.test.domain.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    Optional<TestResult> findByTestIdAndSolverId(Long testId, Long solverId);
}
