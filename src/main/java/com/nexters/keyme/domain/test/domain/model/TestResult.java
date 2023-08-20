package com.nexters.keyme.domain.test.domain.model;

import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.global.model.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_result")
@Data
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
