package com.nexters.keyme.test.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {
    @Override
    public void mappingToResult(Long memberId, Long resultId) {
        /*
            - test resultId로 검색 및 solverId가 존재하는지 확인
            - test resultId update
        */
    }
}