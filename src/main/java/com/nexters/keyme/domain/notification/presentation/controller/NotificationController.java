package com.nexters.keyme.domain.notification.presentation.controller;

import com.nexters.keyme.global.common.dto.response.ApiResponse;
import com.nexters.keyme.domain.notification.presentation.dto.NotificationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.nexters.keyme.global.config.SwaggerConfig.SWAGGER_AUTHORIZATION_SCHEME;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Api(tags = "알림", description = "알림 관련 API")
public class NotificationController {
    @GetMapping
    @ApiOperation(value = "알림 리스트 보기")
    @SecurityRequirement(name = SWAGGER_AUTHORIZATION_SCHEME)
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getMemberInfo(@PathVariable(name = "memberId") Long memberId) {
        NotificationResponse notification = NotificationResponse.builder()
                .notificationId(1L)
                .content("알림 내용")
                .type("QUESTION_SOLVED")
                .resourceId(132L)
                .createdAt(LocalDateTime.now())
                .isRead(false)
                .build();

        return ResponseEntity.ok(ApiResponse.success(List.of(notification)));
    }
}
