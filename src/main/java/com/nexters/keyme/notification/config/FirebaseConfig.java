package com.nexters.keyme.notification.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Value("${fcm.key.path}")
    private String FCM_PRIVATE_KEY_PATH;

    @Bean
    GoogleCredentials googleCredentials() {
            try (InputStream is = new PathResource(FCM_PRIVATE_KEY_PATH).getInputStream()) {
                return GoogleCredentials.fromStream(is);
            } catch (IOException e) {
                try {
                    return GoogleCredentials.getApplicationDefault();
                } catch (IOException e2) {
                    throw new IllegalArgumentException();
                }
            }
    }

    @Bean
    FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }


}
