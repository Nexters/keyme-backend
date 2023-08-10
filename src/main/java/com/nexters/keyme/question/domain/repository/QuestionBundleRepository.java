package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.domain.model.QuestionBundle;
import com.nexters.keyme.question.domain.model.QuestionBundleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionBundleRepository extends JpaRepository<QuestionBundle, QuestionBundleId> {
}
