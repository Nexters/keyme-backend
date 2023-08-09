package com.nexters.keyme.test.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_result")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestResult extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solver_id")
    private MemberEntity solver;

    @OneToMany(mappedBy = "testResult", cascade = CascadeType.REMOVE)
    private List<QuestionSolved> questionSolvedList = new ArrayList<>();

    private Float matchRate;
}
