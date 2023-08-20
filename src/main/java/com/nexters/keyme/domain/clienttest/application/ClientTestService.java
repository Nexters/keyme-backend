package com.nexters.keyme.domain.clienttest.application;

public interface ClientTestService {
    void deleteIssuedOnboardingTest(Long memberId);
    void deleteIssuedDailyTest(Long memberId);
    void clearMember(Long memberId);
}
