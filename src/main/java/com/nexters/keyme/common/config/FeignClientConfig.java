package com.nexters.keyme.common.config;

import com.nexters.keyme.KeymeApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = KeymeApplication.class)
public class FeignClientConfig {
}
