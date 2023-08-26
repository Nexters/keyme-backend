package com.nexters.keyme.global.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponse<T> {
    private long totalCount;
    private boolean hasNext;
    private List<T> results;
}
