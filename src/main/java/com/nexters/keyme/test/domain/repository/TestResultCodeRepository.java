package com.nexters.keyme.test.domain.repository;

import com.nexters.keyme.test.domain.model.TestResultCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultCodeRepository extends JpaRepository<TestResultCode, String> {
}
