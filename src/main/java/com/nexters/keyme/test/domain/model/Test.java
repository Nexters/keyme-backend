package com.nexters.keyme.test.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.question.domain.model.QuestionBundle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test")
@Getter
@NoArgsConstructor
public class Test extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    @Column(columnDefinition = "tinyint(1)")
    @ColumnDefault("0")
    private Boolean isOnboarding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private MemberEntity member;

    @Column(length = 30)
    private String title;

    @OneToMany(mappedBy = "questionBundleId.test", cascade = CascadeType.REMOVE)
    private List<QuestionBundle> questionBundleList = new ArrayList<>();

    @OneToMany(mappedBy = "test", cascade = CascadeType.REMOVE)
    private List<TestResult> testResultList = new ArrayList<>();

    public Test(Boolean isOnboarding, MemberEntity member, String title) {
        this.isOnboarding = isOnboarding;
        this.member = member;
        this.title = title;
    }
}
