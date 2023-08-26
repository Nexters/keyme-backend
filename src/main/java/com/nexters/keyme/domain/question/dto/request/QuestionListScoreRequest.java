package com.nexters.keyme.domain.question.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("Question 점수 요청 객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionListScoreRequest {

    @ApiModelProperty(value = "Question을 출제한 출제자의 Id")
    private Long ownerId;

    @ApiModelProperty(value = "Question을 푼 유저의 Id")
    private Long solverId;

    @ApiModelProperty(value = "가져올 question의 Id들")
    private List<Long> ids;
}