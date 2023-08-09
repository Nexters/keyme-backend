package com.nexters.keyme.clienttest.application;

public interface ClientTestService {
    void deleteIssuedOnboardingTest(Long memberId);
    void deleteIssuedDailyTest(Long memberId);
    void clearMember(Long memberId);
}
