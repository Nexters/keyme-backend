package com.nexters.keyme.test.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.question.domain.model.QuestionBundle;
import com.nexters.keyme.test.presentation.dto.response.QuestionsInTestResponse;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test")
@Getter
public class Test extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    @Column(columnDefinition = "boolean default false")
    private Boolean isOnboarding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "owner_id")
    private MemberEntity member;

    @Column(length = 30)
    private String title;

    @OneToMany(mappedBy = "questionBundleId.test", cascade = CascadeType.REMOVE)
    private List<QuestionBundle> questionBundleList = new ArrayList<>();

    @OneToMany(mappedBy = "test", cascade = CascadeType.REMOVE)
    private List<TestResult> testResultSimpleInfoList = new ArrayList<>();
}
