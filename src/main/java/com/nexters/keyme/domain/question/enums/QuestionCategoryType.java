package com.nexters.keyme.domain.question.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionCategoryType {
    MONEY("돈관리","568049", "https://d2z2a95epq6bmg.cloudfront.net/icon/money.png"),
    PASSION("열정","F37952", "https://d2z2a95epq6bmg.cloudfront.net/icon/passion.png"),
    INTELLIGENCE("지능","D6EC63", "https://d2z2a95epq6bmg.cloudfront.net/icon/intelligence.png"),
    HUMOR("유머","643FFF", "https://d2z2a95epq6bmg.cloudfront.net/icon/humor.png"),
    BODY("신체","EEAFB1", "https://d2z2a95epq6bmg.cloudfront.net/icon/body.png"),
    FOOD("먹거리","A74850", "https://d2z2a95epq6bmg.cloudfront.net/icon/food.png"),
    SENSE("센스","A9DBC3", "https://d2z2a95epq6bmg.cloudfront.net/icon/sense.png"),
    PLANNING("즉흥/계획","BF36FE", "https://d2z2a95epq6bmg.cloudfront.net/icon/planning.png"),
    SENSIBILITY("감성/이성","89B5F6", "https://d2z2a95epq6bmg.cloudfront.net/icon/sensibility.png"),
    RELATIONSHIPS("관계","905CFF", "https://d2z2a95epq6bmg.cloudfront.net/icon/relationships.png"),
    AFFECTION("애정","FB5563", "https://d2z2a95epq6bmg.cloudfront.net/icon/affection.png");

    private String tagName;
    private String color;
    private String imageUrl;
}
