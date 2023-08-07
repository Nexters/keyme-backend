package com.nexters.keyme.statistics.presentation.controller;

import com.nexters.keyme.common.dto.response.ApiResponse;
import com.nexters.keyme.statistics.presentation.dto.request.StatisticRequest;
import com.nexters.keyme.statistics.presentation.dto.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.nexters.keyme.common.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RestController
@RequiredArgsConstructor
@Api(tags = "마이페이지(통계)", description = "멤버 마이페이지 관련 API")
public class StatisticsController {
    @GetMapping("/members/{memberId}/stataistics")
    @ApiOperation(value = "멤버의 성격 통계 보기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<MemberStatisticResponse>> getMemberStatistic(@PathVariable(name = "memberId") Long memberId,
                                                                                   StatisticRequest request) {
        CategoryResponse category = new CategoryResponse("정의로움", "11ACFF", "image Url");
        QuestionResponse question = new QuestionResponse(1L, "키미는 불의를 보면 참는다.", "불의왕", 4.2, category);
        CoordinateResponse coordinate = new CoordinateResponse(0.1213131, -0.242131, 0.12314);
        StatisticResultResponse result = new StatisticResultResponse(question, coordinate);
        MemberStatisticResponse response = new MemberStatisticResponse(14L, List.of(result));

        return ResponseEntity.ok(new ApiResponse<>(response));
    }
}
