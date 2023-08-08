package com.nexters.keyme.question.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import com.nexters.keyme.question.domain.enums.QuestionCategoryType;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String description;

    private String keyword;

    @Enumerated(EnumType.STRING)
    private QuestionCategoryType categoryName;

    @OneToMany(mappedBy = "questionBundleId.question", cascade = CascadeType.REMOVE)
    private List<QuestionBundle> questionBundleList = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionSolved> questionSolvedList = new ArrayList<>();
}
