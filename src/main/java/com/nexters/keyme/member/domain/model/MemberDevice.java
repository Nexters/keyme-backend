package com.nexters.keyme.member.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "member_device")
public class MemberDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
