package com.nexters.keyme.question.domain.internaldto;

import com.nexters.keyme.question.domain.enums.QuestionCategoryType;
import com.nexters.keyme.question.presentation.dto.response.QuestionCategoryResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticInfo {
    private Long questionId;
    private String description;
    private String keyword;
    private QuestionCategoryType categoryName;
    private Double averageScore;
}
