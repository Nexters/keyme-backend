package com.nexters.keyme.domain.test.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TestListType {
    SOLVED, UNSOLVED, MYSOLVED;

    @JsonCreator
    public static TestListType from(String inputValue) {
        return TestListType.valueOf(inputValue);
    }
}
