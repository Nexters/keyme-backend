package com.nexters.keyme.question.domain.model;

import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "question_bundle")
@Getter
public class QuestionBundle {
    @EmbeddedId
    private QuestionBundleId questionBundleId;
}
