package com.nexters.keyme.domain.question.application;

import com.nexters.keyme.domain.member.domain.service.validator.MemberValidator;
import com.nexters.keyme.domain.question.domain.service.validator.QuestionValidator;
import com.nexters.keyme.domain.question.exceptions.NotFoundQuestionException;
import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.dto.response.QuestionResponse;
import com.nexters.keyme.domain.question.dto.response.QuestionScoreInfoResponse;
import com.nexters.keyme.domain.question.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.global.common.dto.internal.PageInfo;
import com.nexters.keyme.global.common.dto.response.PageResponse;
import com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.domain.question.dto.request.QuestionScoreListRequest;
import com.nexters.keyme.domain.question.dto.request.QuestionListScoreRequest;
import com.nexters.keyme.domain.question.dto.request.QuestionStatisticRequest;
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
    private final QuestionValidator questionValidator;
    private final QuestionSolvedRepository questionSolvedRepository;

    private final MemberValidator memberValidator;


    @Override
    public QuestionResponse getQuestion(Long questionId) {
        Question question = questionValidator.validateQuestion(questionId);
        return new QuestionResponse(question);
    }

    @Override
    public QuestionStatisticResponse getQuestionStatistic(Long memberId, Long questionId, QuestionStatisticRequest request) {
        questionValidator.validateQuestion(questionId);
        memberValidator.validateMember(request.getOwnerId());

        QuestionStatisticInfo questionStatisticInfo = questionSolvedRepository.findQuestionStatisticsByQuestionIdAndOwnerId(questionId, request.getOwnerId());
        Integer solverScore = questionSolvedRepository.findSolverScoreByOwnerIdAndQuestionId(memberId, request.getOwnerId(), questionId).orElse(null);

        QuestionStatisticResponse questionStatisticResponse = new QuestionStatisticResponse(questionStatisticInfo);
        questionStatisticResponse.setMyScore(solverScore);

        return questionStatisticResponse;
    }

    @Override
    public PageResponse<QuestionScoreInfoResponse> getQuestionSolvedList(Long questionId, QuestionScoreListRequest request) {
        questionValidator.validateQuestion(questionId);
        memberValidator.validateMember(request.getOwnerId());

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
    @Deprecated
    public List<QuestionScoreInfoResponse> getQuestionSolvedScore(QuestionListScoreRequest request) {
        memberValidator.validateMember(request.getOwnerId());

        List<Question> question = questionRepository.findAllById(request.getIds());
        if (question.size() != request.getIds().size()) {
            throw new NotFoundQuestionException();
        }

        List<QuestionSolved> questionSolvedList = questionSolvedRepository.findByQuestionIdsAndOwnerIdAndSolverId(request.getIds(), request.getOwnerId(), request.getSolverId());

        return questionSolvedList.stream()
                .map(QuestionScoreInfoResponse::new)
                .collect(Collectors.toList());
    }
}