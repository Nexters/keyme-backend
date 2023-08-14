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
import com.nexters.keyme.statistics.presentation.dto.AdditionalStatisticResponse;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private final QuestionRepository questionRepository;
    private final CoordinateConversionService conversionService;

    @Transactional
    @Override
    public Statistic createStatistic(StatisticInfo statisticInfo) {
        Statistic statistic = Statistic.builder()
                .questionId(statisticInfo.getQuestionId())
                .ownerId(statisticInfo.getOwnerId())
                .ownerScore(statisticInfo.getOwnerScore())
                .build();

        return statisticRepository.save(statistic);
    }


    @Transactional
    @Async
    @Override
    public void addNewScores(ScoreInfo scoreInfo) {
        Statistic statistic = statisticRepository.findByOwnerIdAndQuestionIdWithLock(scoreInfo.getOwnerId(), scoreInfo.getQuestionId())
                .orElseGet(() -> {
                    StatisticInfo info = new StatisticInfo(scoreInfo.getOwnerId(), scoreInfo.getQuestionId(), scoreInfo.getScore());
                    return createStatistic(info);
                });

        statistic.addNewScore(scoreInfo.getSolverId(), scoreInfo.getScore());
    }

    @Transactional
    @Override
    public MemberStatisticResponse getMemberStatistic(long memberId, StatisticRequest request) {
        List<Statistic> statistics;

        if (request.getType() == StatisticRequest.StatisticType.DIFFERENT) { // TODO: QueryDsl 등 사용해서 동적쿼리로 처리
            statistics = statisticRepository.findByMemberIdSortByMatchRateAsc(memberId);
        } else {
            statistics = statisticRepository.findByMemberIdSortByMatchRateDesc(memberId);
        }

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

    @Transactional
    @Override
    public List<AdditionalStatisticResponse> getAdditionalStatistics (long memberId, long cursor) {
        List<Statistic> differentStatistics = statisticRepository.findByMemberIdSortByMatchRateAsc(memberId);
        List<Statistic> similarStatistics = statisticRepository.findByMemberIdSortByMatchRateDesc(memberId);

        List<Long> exceptIds = Stream.concat(differentStatistics.stream(), similarStatistics.stream())
                .map(Statistic::getQuestionId)
                .collect(Collectors.toList());

        Statistic cursorStatistic = statisticRepository.findById(cursor)
                .orElseThrow(ResourceNotFoundException::new);

        double cursorScore = cursorStatistic.getSolverAvgScore();

        List<Statistic> statistics = statisticRepository.findExceptIdsSortByAvgScore(exceptIds, cursor, cursorScore);

        return statistics.stream()
                .map((statistic) -> {
                    Question question = questionRepository.findById(statistic.getQuestionId())
                            .orElseThrow(ResourceNotFoundException::new);
                    return new AdditionalStatisticResponse(statistic.getId(), question.getKeyword(), statistic.getSolverAvgScore());
                })
                .collect(Collectors.toList());
    }
}
