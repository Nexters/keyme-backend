package com.nexters.keyme.question.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionCategoryType {
    MONEY("돈관리","568049", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/money.png"),
    PASSION("열정","F37952", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/passion.png"),
    INTELLIGENCE("지능","D6EC63", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/intelligence.png"),
    HUMOR("유머","643FFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/humor.png"),
    BODY("신체","EEAFB1", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/body.png"),
    FOOD("먹거리","A74850", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/food.png"),
    SENSE("센스","A9DBC3", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/sense.png"),
    PLANNING("즉흥/계획","BF36FE", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/planning.png"),
    SENSIBILITY("감성/이성","89B5F6", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/sensibility.png"),
    RELATIONSHIPS("관계","905CFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/relationships.png"),
    AFFECTION("애정","FB5563", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/affection.png");

    private String tagName;
    private String color;
    private String imageUrl;
}
