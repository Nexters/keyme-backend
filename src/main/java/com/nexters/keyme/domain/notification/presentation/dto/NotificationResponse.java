package com.nexters.keyme.domain.notification.presentation.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class NotificationResponse {
    @ApiModelProperty(value="알림 ID", example = "22")
    private final Long notificationId;
    @ApiModelProperty(value="알림 내용", example = "키미님이 내 문제를 풀었어요")
    private final String content;
    @ApiModelProperty(value="알림 타입(추후 정의 예정)", example = "QUESTION_SOLVED")
    private final String type;
    @ApiModelProperty(value="확인 시 이동할 resource Id", example = "22")
    private final Long resourceId;
    @ApiModelProperty(value="생성 일시", example = "22")
    private final LocalDateTime createdAt;
    @ApiModelProperty(value="읽음 여부", example = "true")
    private final boolean isRead;
}
