package com.nexters.keyme.test.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum TestListType {
    SOLVED, UNSOLVED, MYSOLVED;

    @JsonCreator
    public static TestListType from(String inputValue) {
        return Stream.of(TestListType.values())
                .filter(category -> category.toString().equals(inputValue.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
