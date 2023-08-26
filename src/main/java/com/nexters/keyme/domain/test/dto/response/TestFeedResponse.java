package com.nexters.keyme.domain.test.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Test 리스트 내 응답값")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestFeedResponse {
    private Long testId;
    private int solvedMemberCount;
    private String headQuestion;
    private TestSimpleMemberResponse ownerProfile;
    private TestResultRateResponse result;
}
