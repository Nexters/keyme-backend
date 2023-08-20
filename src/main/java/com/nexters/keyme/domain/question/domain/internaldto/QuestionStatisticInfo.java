package com.nexters.keyme.domain.question.domain.internaldto;

import com.nexters.keyme.domain.question.domain.enums.QuestionCategoryType;
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
