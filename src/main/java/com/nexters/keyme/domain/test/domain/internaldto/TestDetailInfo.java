package com.nexters.keyme.domain.test.domain.internaldto;


import com.nexters.keyme.domain.question.domain.internaldto.QuestionInfo;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.domain.test.presentation.dto.response.TestSimpleMemberResponse;
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
