package com.nexters.keyme.domain.statistics.application;

import com.nexters.keyme.domain.statistics.dto.internal.ScoreInfo;
import com.nexters.keyme.domain.statistics.dto.internal.StatisticInfo;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.dto.request.AdditionalStatisticRequest;
import com.nexters.keyme.domain.statistics.dto.response.AdditionalStatisticResponse;
import com.nexters.keyme.domain.statistics.dto.request.StatisticRequest;
import com.nexters.keyme.domain.statistics.dto.response.MemberStatisticResponse;

import java.util.List;

public interface StatisticService {
    Statistic createStatistic(StatisticInfo statisticInfo);
    void addNewScores(ScoreInfo request);

    MemberStatisticResponse getMemberStatistic(long memberId, StatisticRequest request);

    List<AdditionalStatisticResponse> getAdditionalStatistics (long memberId, AdditionalStatisticRequest request);
}
