package com.nexters.keyme.domain.statistics.presentation.controller;

import com.nexters.keyme.domain.statistics.application.StatisticService;
import com.nexters.keyme.domain.statistics.dto.response.AdditionalStatisticResponse;
import com.nexters.keyme.domain.statistics.dto.request.StatisticRequest;
import com.nexters.keyme.global.common.dto.response.ApiResponse;
import com.nexters.keyme.domain.statistics.dto.request.AdditionalStatisticRequest;
import com.nexters.keyme.domain.statistics.dto.response.MemberStatisticResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.nexters.keyme.global.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RestController
@RequiredArgsConstructor
@Api(tags = "마이페이지(통계)", description = "멤버 마이페이지 관련 API")
public class StatisticController {
    private final StatisticService statisticService;
    @GetMapping("/members/{memberId}/statistics")
    @ApiOperation(value = "멤버의 성격 통계 보기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<MemberStatisticResponse>> getMemberStatistic(@PathVariable(name = "memberId") Long memberId,
                                                                                   StatisticRequest request) {
        MemberStatisticResponse response = statisticService.getMemberStatistic(memberId, request);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }

    @GetMapping("/members/{memberId}/statistics/more")
    @ApiOperation(value = "멤버의 성격 더보기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<List<AdditionalStatisticResponse>>> getMemberStatistic(@PathVariable(name = "memberId") Long memberId,
                                                                                             AdditionalStatisticRequest request) {
        List<AdditionalStatisticResponse> response = statisticService.getAdditionalStatistics(memberId, request);
        return ResponseEntity.ok(new ApiResponse<>(response));
    }
}
