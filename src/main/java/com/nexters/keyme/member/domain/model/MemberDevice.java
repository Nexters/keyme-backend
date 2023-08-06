package com.nexters.keyme.member.domain.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "member_device")
@Getter
public class MemberDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
