package com.nexters.keyme.test.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum TestListType {
    SOLVED, UNSOLVED, MYSOLVED;

    @JsonCreator
    public static TestListType from(String inputValue) {
        return TestListType.valueOf(inputValue);
    }
}
