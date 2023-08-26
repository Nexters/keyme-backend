package com.nexters.keyme.domain.question.dto.internal;

import com.nexters.keyme.domain.question.enums.QuestionCategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticInfo {
    private Long questionId;
    private String title;
    private String keyword;
    private QuestionCategoryType categoryName;
    private Double avgScore;
}
