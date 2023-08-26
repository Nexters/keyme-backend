package com.nexters.keyme.domain.test.dto.internal;


import com.nexters.keyme.domain.question.dto.internal.QuestionInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDetailInfo {
    private Long testId;
    private String title;
    private TestOwnerInfo owner;
    private List<QuestionInfo> questions;
}
