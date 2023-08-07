package com.nexters.keyme.test.domain.repository;

import com.nexters.keyme.test.domain.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultSimpleInfoRepository extends JpaRepository<TestResult, Long> {
}
