package com.nexters.keyme.domain.question.presentation.dto.request;

import com.nexters.keyme.global.dto.request.PaginationRequest;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Question을 푼 사람들의 점수리스트 요청 객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionScoreListRequest extends PaginationRequest {
    private Long ownerId;
}
