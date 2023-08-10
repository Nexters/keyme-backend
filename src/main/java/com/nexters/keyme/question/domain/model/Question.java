package com.nexters.keyme.question.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.question.domain.enums.QuestionCategoryType;
import com.nexters.keyme.question.presentation.dto.response.QuestionCategoryResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String description;

    @Column(columnDefinition = "bit")
    @ColumnDefault("0")
    private Boolean isOnboarding;

    private String keyword;

    @Enumerated(EnumType.STRING)
    private QuestionCategoryType categoryName;

    @OneToMany(mappedBy = "questionBundleId.question", cascade = CascadeType.REMOVE)
    private List<QuestionBundle> questionBundleList = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionSolved> questionSolvedList = new ArrayList<>();

    public Question(Long questionId) {
        this.questionId = questionId;
    }
}
