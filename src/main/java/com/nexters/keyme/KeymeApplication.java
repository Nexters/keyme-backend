package com.nexters.keyme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class KeymeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KeymeApplication.class, args);
	}
}
