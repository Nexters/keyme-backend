package com.nexters.keyme.test.presentation.dto.request;

import com.nexters.keyme.common.dto.request.PaginationRequest;
import com.nexters.keyme.test.domain.enums.TestListType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel(value = "테스트 리스트 파라미터 객체")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TestListRequest extends PaginationRequest {

    @ApiModelProperty(value = "받아오 리스트의 타입", example = "SOLVED", required = true)
    private TestListType type = TestListType.SOLVED;
}
