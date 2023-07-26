package com.nexters.keyme.member.domain;

import com.nexters.keyme.common.domain.BaseTimeEntity;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberOAuthEntity> memberOAuthList = new ArrayList<>();
}
