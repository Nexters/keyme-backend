package com.nexters.keyme.domain.question.domain.repository;

import com.nexters.keyme.domain.question.domain.model.QuestionBundle;
import com.nexters.keyme.domain.question.domain.model.QuestionBundleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBundleRepository extends JpaRepository<QuestionBundle, QuestionBundleId> {
}
