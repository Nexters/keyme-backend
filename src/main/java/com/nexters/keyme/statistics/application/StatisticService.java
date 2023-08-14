package com.nexters.keyme.statistics.application;

import com.nexters.keyme.statistics.application.dto.ScoreInfo;
import com.nexters.keyme.statistics.domain.internaldto.StatisticInfo;
import com.nexters.keyme.statistics.domain.model.Statistic;
import com.nexters.keyme.statistics.presentation.dto.AdditionalStatisticResponse;
import com.nexters.keyme.statistics.presentation.dto.request.StatisticRequest;
import com.nexters.keyme.statistics.presentation.dto.response.MemberStatisticResponse;

import java.util.List;

public interface StatisticService {
    Statistic createStatistic(StatisticInfo statisticInfo);
    void addNewScores(ScoreInfo request);

    MemberStatisticResponse getMemberStatistic(long memberId, StatisticRequest request);

    List<AdditionalStatisticResponse> getAdditionalStatistics (long memberId, long cursor);
}
