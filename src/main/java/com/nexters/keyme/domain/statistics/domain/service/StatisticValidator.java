package com.nexters.keyme.domain.statistics.domain.service;

import com.nexters.keyme.domain.member.domain.service.validator.MemberValidator;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.exceptions.NotEnoughStatisticsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticValidator {
    private final MemberValidator memberValidator;

    public void validateStatistics(List<Statistic> statistics, Long memberId) {
        checkIsMemberNotDeleted(memberId);
        checkMinimumSize(statistics);
    }

    private void checkIsMemberNotDeleted(Long memberId) {
        memberValidator.validateMember(memberId);
    }

    private void checkMinimumSize(List<Statistic> statistics) {
        if (statistics.size() < 5) {
            throw new NotEnoughStatisticsException();
        }
    }
}
