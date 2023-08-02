package com.nexters.keyme.test.domain;

import com.nexters.keyme.common.domain.BaseTimeEntity;
import com.nexters.keyme.member.domain.model.MemberEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "test_result_simple_info")
@Getter
public class TestResultSimpleInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testResultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solver_id")
    private MemberEntity member;

    private Float matchRate;
}
