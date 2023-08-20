package com.nexters.keyme.domain.statistics.application;

import com.nexters.keyme.domain.statistics.application.dto.ScoreInfo;
import com.nexters.keyme.domain.statistics.domain.internaldto.StatisticInfo;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.presentation.dto.AdditionalStatisticRequest;
import com.nexters.keyme.domain.statistics.presentation.dto.AdditionalStatisticResponse;
import com.nexters.keyme.domain.statistics.presentation.dto.request.StatisticRequest;
import com.nexters.keyme.domain.statistics.presentation.dto.response.MemberStatisticResponse;

import java.util.List;

public interface StatisticService {
    Statistic createStatistic(StatisticInfo statisticInfo);
    void addNewScores(ScoreInfo request);

    MemberStatisticResponse getMemberStatistic(long memberId, StatisticRequest request);

    List<AdditionalStatisticResponse> getAdditionalStatistics (long memberId, AdditionalStatisticRequest request);
}
