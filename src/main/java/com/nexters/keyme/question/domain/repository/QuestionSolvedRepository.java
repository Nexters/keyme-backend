package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.question.domain.model.QuestionSolved;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionSolvedRepository extends JpaRepository<QuestionSolved, Long> {
}
