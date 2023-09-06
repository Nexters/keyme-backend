package com.nexters.keyme.domain.statistics.application;

import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import com.nexters.keyme.domain.question.exceptions.NotFoundQuestionException;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.domain.repository.StatisticRepository;
import com.nexters.keyme.domain.statistics.domain.service.CoordinateConversionService;
import com.nexters.keyme.domain.statistics.dto.internal.CoordinateInfo;
import com.nexters.keyme.domain.statistics.dto.internal.ScoreInfo;
import com.nexters.keyme.domain.statistics.dto.internal.StatisticInfo;
import com.nexters.keyme.domain.statistics.dto.request.AdditionalStatisticRequest;
import com.nexters.keyme.domain.statistics.dto.request.StatisticRequest;
import com.nexters.keyme.domain.statistics.dto.response.*;
import com.nexters.keyme.domain.statistics.exceptions.NotEnoughStatisticsException;
import com.nexters.keyme.domain.statistics.exceptions.NotFoundStatisticsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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
    @Override
    public void addNewScores(ScoreInfo scoreInfo) {
        // FIXME - 내부에서만 사용중 domain service로 뺄것
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

        if (checkStatisticExists(statistics)) {
            throw new NotEnoughStatisticsException();
        }

        statistics.sort(getStatisticComparator());

        List<CoordinateInfo> coordinates = conversionService.convertFrom(statistics);

        List<StatisticResultResponse> results = new ArrayList<>();

        for (int i = 0; i < statistics.size(); i++) {
            Statistic statistic = statistics.get(i);
            CoordinateInfo coordinateInfo = coordinates.get(i);

            Question question = questionRepository.findById(statistic.getQuestionId())
                    .orElseThrow(NotFoundQuestionException::new);

            results.add(new StatisticResultResponse(new StatisticQuestionResponse(question, statistic.getOwnerScore(), statistic.getSolverAvgScore()), new CoordinateResponse(coordinateInfo)));
        }

        return new MemberStatisticResponse(memberId, results);
    }

    private static Comparator<Statistic> getStatisticComparator() {
        return (s1, s2) -> {
            if (s1.getSolverAvgScore() > s2.getSolverAvgScore()) {
                return 1;
            } else if (s1.getSolverAvgScore() < s2.getSolverAvgScore()) {
                return -1;
            }
            return 0;
        };
    }

    private boolean checkStatisticExists(List<Statistic> statistics) {
        return statistics.size() < 5;
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
