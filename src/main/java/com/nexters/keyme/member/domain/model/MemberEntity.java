package com.nexters.keyme.member.domain.model;

import com.nexters.keyme.common.domain.BaseTimeEntity;
import com.nexters.keyme.question.domain.QuestionSolved;
import com.nexters.keyme.test.domain.Test;
import com.nexters.keyme.test.domain.TestResultSimpleInfo;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OauthInfo> oauthInfo;
    @OneToMany(fetch = FetchType.LAZY)
    private List<DeviceInfo> deviceInfo;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Test> testList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<QuestionSolved> questionSolvedList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<TestResultSimpleInfo> testResultSimpleInfoList = new ArrayList<>();

    private String nickname;
    private String inviteCode;
    private MemberStatus status;
    private ProfileImage image;
}
