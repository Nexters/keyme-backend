package com.nexters.keyme.question.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionCategoryType {
    인간관계("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    사회적_활동("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    레저_및_여가("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    성격_및_특성("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    감정_표현("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    의사_표현("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    성취_및_도전("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    창의성("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    예술과_문화("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    문제_해결("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    삶의_변화("FFFFFF", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png"),
    개인적_성향("222222", "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png");

    private String color;
    private String imageUrl;
}
