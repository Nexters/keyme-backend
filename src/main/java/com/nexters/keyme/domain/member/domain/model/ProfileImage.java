package com.nexters.keyme.domain.member.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImage {
    private String originalUrl;
    private String thumbnailUrl;
}
