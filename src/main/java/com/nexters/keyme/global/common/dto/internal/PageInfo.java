package com.nexters.keyme.global.common.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageInfo<T> {
    private long totalCount;
    private boolean hasNext;
    private List<T> results;
}
