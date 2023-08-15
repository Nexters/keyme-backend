package com.nexters.keyme.question.application;

import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.question.domain.repository.QuestionRepository;
import com.nexters.keyme.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedListRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionStatisticRequest;
import com.nexters.keyme.question.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionSolvedRepository questionSolvedRepository;
    private final MemberRepository memberRepository;

    @Override
    public QuestionResponse getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
        return new QuestionResponse(question);
    }

    @Override
    public QuestionScoreResponse getQuestionSolvedScore(Long questionId, QuestionSolvedRequest request) {
        Question question = questionRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
        MemberEntity member = memberRepository.findById(request.getOwnerId()).orElseThrow(ResourceNotFoundException::new);
        QuestionSolved questionSolved = questionSolvedRepository.findByQuestionAndOwner(question, member).orElseThrow(ResourceNotFoundException::new);
        return new QuestionScoreResponse(questionSolved.getScore());
    }

    @Override
    public QuestionStatisticResponse getQuestionStatistic(Long questionId, QuestionStatisticRequest request) {
        Question question = questionRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
        MemberEntity member = memberRepository.findById(request.getOwnerId()).orElseThrow(ResourceNotFoundException::new);
        QuestionStatisticInfo questionStatisticInfo = questionSolvedRepository.findQuestionStatisticsByQuestionIdAndOwnerId(questionId, request.getOwnerId()).orElseThrow(ResourceNotFoundException::new);

        // questionStatisticInfo에 값들이 null일 수 있음(푼 사람이 아무도 없을때)
        if (questionStatisticInfo.getCategoryName() == null) {
            return new QuestionStatisticResponse(question);
        }

        return new QuestionStatisticResponse(questionStatisticInfo);
    }

    @Override
    public QuestionSolvedListResponse getQuestionSolvedList(Long questionId, QuestionSolvedListRequest request) {
        Question question = questionRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
        MemberEntity member = memberRepository.findById(request.getOwnerId()).orElseThrow(ResourceNotFoundException::new);

        Page<QuestionSolved> solvedPage = questionSolvedRepository.findQuestionSolvedList(questionId, request.getOwnerId(), request.getCursor(), request.getLimit());

        return new QuestionSolvedListResponse(solvedPage);
    }
}
