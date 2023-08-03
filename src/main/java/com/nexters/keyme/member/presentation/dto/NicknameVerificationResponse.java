package com.nexters.keyme.member.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NicknameVerificationResponse {
    private final boolean isValid;
    private String description;
}
