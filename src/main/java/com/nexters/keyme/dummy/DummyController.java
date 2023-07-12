package com.nexters.keyme.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DummyController {

    private final DummyService dummyService;

    @GetMapping("/")
    public String helloKeyme() {
        return "Hello keyme!";
    }

    @GetMapping("/notification")
    public String sendNotification() {
        dummyService.doLogicAndPublishNotification();
        return "requested notification";
    }
}
