package com.nexters.keyme.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DummyController {

    @GetMapping("/hello")
    private final DummyService dummyService;

    @GetMapping("/auth")
    public String helloAuthKeyme() {
        return "Hello Authenticated keyme!";
    }
  
    @GetMapping("/notification")
    public String sendNotification() {
        dummyService.doLogicAndPublishNotification();
        return "requested notification";
    }
}
