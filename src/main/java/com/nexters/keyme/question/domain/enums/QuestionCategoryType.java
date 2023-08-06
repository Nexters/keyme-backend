package com.nexters.keyme.question.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum QuestionCategoryType {
    댕청함("FFFFFF", ""),
    낭만("000000", ""),
    무지성("222222", "");

    private String colorCode;
    private String iamgeUrl;
}
