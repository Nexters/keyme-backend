package com.nexters.keyme.common.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestLogInfo {
    private Long requestMemberId;
    private String uri;
    private String method;
    private String param;
    private String body;
    private long executionTime;
}
