package com.nexters.keyme.member.domain.model;

import com.nexters.keyme.common.domain.BaseTimeEntity;
import com.nexters.keyme.member.domain.internaldto.MemberModificationInfo;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<MemberOauth> memberOauth;
    @OneToMany(fetch = FetchType.LAZY)
    private List<MemberDevice> memberDevice;

    private String nickname;
    private String inviteCode;
    private MemberStatus status;
    private ProfileImage profileImage;

    public void modifyMemberInfo(MemberModificationInfo modificationInfo) {
        this.nickname = modificationInfo.getNickname();
        this.profileImage = new ProfileImage(modificationInfo.getOriginalImage(), modificationInfo.getThumbnailImage());
    }
}
