package com.nexters.keyme.member.domain.model;

import com.nexters.keyme.common.domain.BaseTimeEntity;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.test.domain.model.Test;
import com.nexters.keyme.test.domain.model.TestResultSimpleInfo;
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

    private String nickname;
    private String inviteCode;
    private MemberStatus status;
    private ProfileImage image;
}
