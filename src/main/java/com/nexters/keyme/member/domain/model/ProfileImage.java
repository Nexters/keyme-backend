package com.nexters.keyme.member.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImage {
    private String originalUrl;
    private String thumbnailUrl;
}
