package com.nexters.keyme.member.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.member.domain.internaldto.MemberModificationInfo;
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="member_oauth_id")
    private List<MemberOAuth> memberOauth;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="member_device_id")
    private List<MemberDevice> memberDevice;

    private String nickname;
    private String friendCode;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private ProfileImage profileImage;

    public void modifyMemberInfo(MemberModificationInfo modificationInfo) {
        this.nickname = modificationInfo.getNickname();
        this.profileImage = new ProfileImage(modificationInfo.getOriginalImage(), modificationInfo.getThumbnailImage());
    }
}
