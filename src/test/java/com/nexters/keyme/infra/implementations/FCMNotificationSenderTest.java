package com.nexters.keyme.infra.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
@ActiveProfiles("test")
class FCMNotificationSenderTest {
    @Autowired
    private FCMNotificationSender fcmNotificationSender;

//    @Test
//    @DisplayName("토큰이 없는 유저 발송 테스트")
    void sendByTokens() {
        fcmNotificationSender.sendByTokens(new ArrayList<>(), "test title", "test body", new HashMap<>());
    }
}