package com.nexters.keyme.test.domain.model;

import com.nexters.keyme.common.domain.BaseTimeEntity;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.question.domain.model.QuestionBundle;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "owner_id")
    private MemberEntity member;

    @Column(length = 30)
    private String title;

    @OneToMany(mappedBy = "questionBundleId.test", cascade = CascadeType.REMOVE)
    private List<QuestionBundle> questionBundleList = new ArrayList<>();

    @OneToMany(mappedBy = "test", cascade = CascadeType.REMOVE)
    private List<TestResultSimpleInfo> testResultSimpleInfoList = new ArrayList<>();
}
