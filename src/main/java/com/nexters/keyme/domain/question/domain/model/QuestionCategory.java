package com.nexters.keyme.domain.question.domain.model;

import com.nexters.keyme.global.common.model.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "question_category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionCategoryId;
    private String name;
    private String color;
    private String iconUrl;
}
