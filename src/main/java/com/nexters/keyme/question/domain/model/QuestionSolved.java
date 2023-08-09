package com.nexters.keyme.question.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.question.presentation.dto.response.QuestionCategoryResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionSolvedResponse;
import com.nexters.keyme.test.domain.model.TestResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "question_solved")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionSolved extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionSolvedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_result_id")
    private TestResult testResult;

    @Min(0)
    @Max(5)
    private int score;
}
