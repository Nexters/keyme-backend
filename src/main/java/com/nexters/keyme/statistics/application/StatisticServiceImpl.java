package com.nexters.keyme.statistics.application;

import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.domain.repository.QuestionRepository;
import com.nexters.keyme.statistics.application.dto.ScoreInfo;
import com.nexters.keyme.statistics.domain.internaldto.CoordinateInfo;
import com.nexters.keyme.statistics.domain.internaldto.StatisticInfo;
import com.nexters.keyme.statistics.domain.model.Statistic;
import com.nexters.keyme.statistics.domain.repository.StatisticRepository;
import com.nexters.keyme.statistics.domain.service.CoordinateConversionService;
import com.nexters.keyme.statistics.presentation.dto.request.StatisticRequest;
import com.nexters.keyme.statistics.presentation.dto.response.CoordinateResponse;
import com.nexters.keyme.statistics.presentation.dto.response.MemberStatisticResponse;
import com.nexters.keyme.statistics.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.statistics.presentation.dto.response.StatisticResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private final QuestionRepository questionRepository;
    private final CoordinateConversionService conversionService;

    public void createStatistic(StatisticInfo statisticInfo) {

    }


    @Transactional
    @Async
    @Override
    public void addNewScores(ScoreInfo scoreInfo) {
        Statistic statistic = statisticRepository.findByOwnerIdAndQuestionIdWithLock(scoreInfo.getOwnerId(), scoreInfo.getQuestionId())
                .orElseThrow(ResourceNotFoundException::new);
        statistic.addNewScore(scoreInfo.getScore());
    }

    @Override
    public MemberStatisticResponse getMemberStatistic(long memberId, StatisticRequest request) {
        List<Statistic> statistics = statisticRepository.findByMemberId(memberId);
        List<CoordinateInfo> coordinates = conversionService.convertFrom(statistics);

        List<StatisticResultResponse> results = new ArrayList<>();

        for (int i = 0; i < statistics.size(); i++) {
            Statistic statistic = statistics.get(i);
            CoordinateInfo coordinateInfo = coordinates.get(i);

            Question question = questionRepository.findById(statistic.getQuestionId())
                    .orElseThrow(ResourceNotFoundException::new);

            results.add(new StatisticResultResponse(new QuestionResponse(question, statistic.getSolverAvgScore()), new CoordinateResponse(coordinateInfo)));
        }

        return new MemberStatisticResponse(memberId, results);
    }
}
