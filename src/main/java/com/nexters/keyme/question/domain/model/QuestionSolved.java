package com.nexters.keyme.question.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.member.domain.model.MemberEntity;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "question_solved")
@Getter
public class QuestionSolved extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionSolvedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solver_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_owner_id")
    private MemberEntity ownerMember;

    @Min(0)
    @Max(5)
    private int score;
}
