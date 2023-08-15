package com.nexters.keyme.member.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "member_device")
@Getter
public class MemberDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_device_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private String token;
}
