package com.nexters.keyme.test.application;

import com.nexters.keyme.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.test.presentation.dto.request.TestResultRequest;
import com.nexters.keyme.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.test.presentation.dto.response.QuestionsInTestResponse;
import com.nexters.keyme.test.presentation.dto.response.TestFeedResponse;
import com.nexters.keyme.test.presentation.dto.response.TestResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    @Override
    public QuestionsInTestResponse getDailyTest(Long memberId) {
        /*
            - 안 푼 데일리 테스트 있는지 확인
                - 해당 user의 가장 최근 test 가져옴
                - 안 풀었으면 그걸로 리턴
                - 풀었음
                    - 오늘 날짜면 그걸로 리턴
                    - 오늘 날짜가 아니면 발급 로직으로
            - 데일리 테스트 발급
                - (member가 푼 문제, 모든 문제의 Id 최대값) 가져오기
                - 안 푼 id들 배열에 넣고 랜덤 index로 3개 선택
                - 하나의 test로 만들기
            - 각 id로 question들 가져와서 리턴
        */
        return null;
    }

    @Override
    public QuestionsInTestResponse getOnboardingTest() {
        /*
            - 해당 유저의 onboarding test가 존재하는지 확인(test의 isOnboarding)
            - 있다면 해당 test + 풀었는지도 확인해서 isSovled에 포함해서 리턴
            - 없다면 생성 후 리턴
        */
        return null;
    }

    @Override
    public QuestionsInTestResponse getSpecificTest(Long memberId, Long testId) {
        /*
            - testId로 검색 + 해당 테스트를 내가 풀었는지도 검색해서 리턴
        */
        return null;
    }

    // Not MMVP
    @Override
    public List<TestFeedResponse> getTestList(Long memberId, TestListRequest requestInfo) {
        return null;
    }

    @Override
    public TestResultResponse submitTest(Long solverId, Long testId, TestSubmissionRequest submitInfo) {
        /*
            - 해당 member가 해당 test를 이미 풀었는지 확인(presenterID 반환)
            - question solved에 모두 추가
            - matchRate 계산(solverId == presenterId -> 100) 후 test result 저장
            - test result에 question solved 담아서 반환
        */
        return null;
    }

    @Override
    public TestResultResponse getTestResult(Long solverId, Long testId) {
        /*
            - testId와 solverId로 testResult 검색
            - 관련된 question Solved 모두 검색
            - testResult에 question Solved 담고 isSolved = true로 해서 반환
        */
        return null;
    }
}
