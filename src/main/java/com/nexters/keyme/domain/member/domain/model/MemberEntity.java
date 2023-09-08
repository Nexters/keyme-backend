package com.nexters.keyme.domain.member.domain.model;

import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.global.common.model.BaseTimeEntity;
import com.nexters.keyme.domain.member.dto.internal.MemberModificationInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="member_oauth_id")
    private List<MemberOAuth> memberOauth;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="member_id")
    private List<MemberDevice> memberDevice;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Test> testList;

    private String nickname;
    private String friendCode;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private ProfileImage profileImage;
    private boolean isDeleted;


    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void modifyMemberInfo(MemberModificationInfo modificationInfo) {
        if (modificationInfo.getNickname() != null) {
            this.nickname = modificationInfo.getNickname();
        }

        if (modificationInfo.getOriginalImage() != null && modificationInfo.getThumbnailImage() != null) {
            this.profileImage = new ProfileImage(modificationInfo.getOriginalImage(), modificationInfo.getThumbnailImage());
        }
    }
}
