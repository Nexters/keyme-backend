package com.nexters.keyme.common.dto.response;

import com.nexters.keyme.common.dto.internal.PageInfo;
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
