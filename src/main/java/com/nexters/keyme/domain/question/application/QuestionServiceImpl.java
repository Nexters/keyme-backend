package com.nexters.keyme.domain.question.application;

import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionScoreInfoResponse;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.global.dto.internal.PageInfo;
import com.nexters.keyme.global.dto.response.PageResponse;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import com.nexters.keyme.domain.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.domain.question.presentation.dto.request.QuestionScoreListRequest;
import com.nexters.keyme.domain.question.presentation.dto.request.QuestionListScoreRequest;
import com.nexters.keyme.domain.question.presentation.dto.request.QuestionStatisticRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public PageResponse<QuestionScoreInfoResponse> getQuestionSolvedList(Long questionId, QuestionScoreListRequest request) {
        Question question = questionRepository.findById(questionId).orElseThrow(ResourceNotFoundException::new);
        MemberEntity member = memberRepository.findById(request.getOwnerId()).orElseThrow(ResourceNotFoundException::new);

        PageInfo<QuestionSolved> solvedPage = questionSolvedRepository.findQuestionSolvedList(questionId, request.getOwnerId(), request.getCursor(), request.getLimit());

        return new PageResponse(
            solvedPage.getTotalCount(),
            solvedPage.isHasNext(),
            solvedPage.getResults().stream()
                    .map(QuestionScoreInfoResponse::new)
                    .collect(Collectors.toList())
        );
    }

    @Override
    public List<QuestionScoreInfoResponse> getQuestionSolvedScore(QuestionListScoreRequest request) {
        List<Question> question = questionRepository.findAllById(request.getIds());
        if (question.size() != request.getIds().size()) {
            throw new ResourceNotFoundException();
        }

        MemberEntity member = memberRepository.findById(request.getOwnerId()).orElseThrow(ResourceNotFoundException::new);
        List<QuestionSolved> questionSolvedList = questionSolvedRepository.findByQuestionIdsAndOwnerIdAndSolverId(request.getIds(), request.getOwnerId(), request.getSolverId());

        return questionSolvedList.stream()
                .map(QuestionScoreInfoResponse::new)
                .collect(Collectors.toList());
    }
}