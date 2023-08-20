package com.nexters.keyme.domain.question.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "question_bundle")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBundle {
    @EmbeddedId
    private QuestionBundleId questionBundleId;
}
