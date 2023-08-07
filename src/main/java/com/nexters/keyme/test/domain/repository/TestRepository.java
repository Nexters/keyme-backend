package com.nexters.keyme.test.domain.repository;

import com.nexters.keyme.test.domain.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
