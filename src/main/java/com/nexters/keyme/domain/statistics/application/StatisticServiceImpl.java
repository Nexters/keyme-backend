package com.nexters.keyme.domain.statistics.application;

import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import com.nexters.keyme.domain.question.exceptions.NotFoundQuestionException;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.domain.repository.StatisticRepository;
import com.nexters.keyme.domain.statistics.domain.service.CoordinateConversionService;
import com.nexters.keyme.domain.statistics.domain.service.StatisticValidator;
import com.nexters.keyme.domain.statistics.dto.internal.CoordinateInfo;
import com.nexters.keyme.domain.statistics.dto.internal.ScoreInfo;
import com.nexters.keyme.domain.statistics.dto.internal.StatisticInfo;
import com.nexters.keyme.domain.statistics.dto.request.AdditionalStatisticRequest;
import com.nexters.keyme.domain.statistics.dto.request.StatisticRequest;
import com.nexters.keyme.domain.statistics.dto.response.*;
import com.nexters.keyme.domain.statistics.exceptions.NotFoundStatisticsException;
import lombok.RequiredArgsConstructor;
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
    private final StatisticValidator statisticValidator;

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
    @Override
    public void addNewScores(ScoreInfo scoreInfo) {
        Statistic statistic = statisticRepository.findByOwnerIdAndQuestionId(scoreInfo.getOwnerId(), scoreInfo.getQuestionId())
                .orElseGet(() -> {
                    StatisticInfo info = new StatisticInfo(scoreInfo.getOwnerId(), scoreInfo.getQuestionId(), scoreInfo.getScore());
                    return createStatistic(info);
                });

        Statistic statisticWithLock = statisticRepository.findByIdWithLock(statistic.getId())
                        .orElseThrow(NotFoundStatisticsException::new);

        statisticWithLock.addNewScore(scoreInfo.getSolverId(), scoreInfo.getScore());
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

        statisticValidator.validateStatistics(statistics, memberId);

        List<CoordinateInfo> coordinates = conversionService.convertFrom(statistics);
        List<StatisticResultResponse> results = createStatisticResultResponse(statistics, coordinates);
        return new MemberStatisticResponse(memberId, results);
    }

    private List<StatisticResultResponse> createStatisticResultResponse(List<Statistic> statistics, List<CoordinateInfo> coordinates) {
        List<StatisticResultResponse> results = new ArrayList<>();

        for (int i = 0; i < statistics.size(); i++) {
            Statistic statistic = statistics.get(i);
            CoordinateInfo coordinateInfo = coordinates.get(i);

            Question question = questionRepository.findById(statistic.getQuestionId())
                    .orElseThrow(NotFoundQuestionException::new);

            results.add(new StatisticResultResponse(new StatisticQuestionResponse(question, statistic.getOwnerScore(), statistic.getSolverAvgScore()), new CoordinateResponse(coordinateInfo)));
        }
        return results;
    }

    @Transactional
    @Override
    public List<AdditionalStatisticResponse> getAdditionalStatistics (long memberId, AdditionalStatisticRequest request) {
        List<Statistic> differentStatistics = statisticRepository.findByMemberIdSortByMatchRateAsc(memberId);
        List<Statistic> similarStatistics = statisticRepository.findByMemberIdSortByMatchRateDesc(memberId);

        List<Long> exceptIds = Stream.concat(differentStatistics.stream(), similarStatistics.stream())
                .map(Statistic::getQuestionId)
                .collect(Collectors.toList());

        Statistic cursorStatistic = getCursorStatistic(request.getCursor());
        double cursorScore = cursorStatistic.getSolverAvgScore();

        List<Statistic> statistics = statisticRepository.findExceptIdsSortByAvgScore(exceptIds, request.getCursor(), cursorScore, request.getLimit());

        return statistics.stream()
                .map((statistic) -> {
                    Question question = questionRepository.findById(statistic.getQuestionId())
                            .orElseThrow(NotFoundQuestionException::new);
                    return new AdditionalStatisticResponse(question.getQuestionId(), question.getKeyword(), question.getCategoryName().getColor(), question.getCategoryName().getImageUrl(), statistic.getSolverAvgScore());
                })
                .collect(Collectors.toList());
    }

    private Statistic getCursorStatistic(long cursor) {
        if (cursor == 0) {
            return Statistic.builder()
                    .id(0)
                    .solverAvgScore(Double.MAX_VALUE)
                    .build();

        }

        return statisticRepository.findById(cursor)
                    .orElseThrow(NotFoundStatisticsException::new);
    }
}
