package com.nexters.keyme.domain.member.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class ValidationInfo {
    private final boolean isValid;
    private String message;
}
