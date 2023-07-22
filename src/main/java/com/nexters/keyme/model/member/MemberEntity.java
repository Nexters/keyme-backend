package com.nexters.keyme.model.member;

import com.nexters.keyme.model.BaseTimeEntity;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
